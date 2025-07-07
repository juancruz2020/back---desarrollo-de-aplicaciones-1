package com.uade.tpo.app.app.service;

import com.uade.tpo.app.app.model.*;
import com.uade.tpo.app.app.dto.*;
import com.uade.tpo.app.app.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private TipoRecetaRepository tipoRecetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private UtilizadoRepository utilizadoRepository;

    @Autowired
    private PasoRepository pasoRepository;

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private ImagenService imagenService;

    public List<RecetaDTO> listarRecetas() {
        List<Receta> recetas = recetaRepository.findAll();
        List<RecetaDTO> dtos = new ArrayList<>();

        for (Receta receta : recetas) {

            System.out.println("DEBUG: Receta ID: " + receta.getIdReceta() + ", Foto Principal: " + receta.getFotoPrincipal());

            RecetaDTO dto = new RecetaDTO();
            dto.setIdReceta(receta.getIdReceta());
            dto.setNombre(receta.getNombreReceta());
            dto.setDescripcion(receta.getDescripcionReceta());
            dto.setUrlImagen(receta.getFotoPrincipal());
            if (receta.getTipoReceta() != null) {
                dto.setCategoria(receta.getTipoReceta().getDescripcion());
            } else {
                // Puedes elegir alguna de las siguientes:
                dto.setCategoria("Sin categoría");
                // Opcional: loguear para corregir tus datos sucios en base
                System.err.println("ADVERTENCIA: La receta con id " + receta.getIdReceta() + " no tiene categoría asignada.");
            }

            dto.setPorciones(receta.getPorciones() == 0.0 ? 0 : receta.getPorciones());
            dtos.add(dto);
        }

        return dtos;
    }
    public List<Receta> filtrarPorNombre(String nombre) {
        return recetaRepository.findByNombreRecetaContainingIgnoreCase(nombre);
    }

    public List<Receta> filtrarPorCategoria(String categoria) {
        Optional<TipoReceta> tipo = tipoRecetaRepository.findByDescripcionIgnoreCase(categoria);
        return tipo.map(value -> recetaRepository.findByTipoRecetaIdTipo(value.getIdTipo())).orElse(Collections.emptyList());
    }

    public List<Receta> filtrarPorIngrediente(String nombreIngrediente, boolean contiene) {
        Optional<Ingrediente> ingredientes = ingredienteRepository.findByNombreIgnoreCase(nombreIngrediente);
        if (ingredientes.isEmpty()) return Collections.emptyList();

        List<Long> ids = ingredientes.stream().map(Ingrediente::getIdIngrediente).collect(Collectors.toList());
        List<Utilizado> utilizados = utilizadoRepository.findByIngredienteIdIngredienteIn(ids);

        Set<Long> recetaIds = utilizados.stream().map(u -> u.getReceta().getIdReceta()).collect(Collectors.toSet());

        return recetaRepository.findAllById(recetaIds);
    }

    public List<Receta> filtrarPorUsuario(String nickname) {
        Optional<Usuario> usuario = usuarioRepository.findByNicknameIgnoreCase(nickname);
        return usuario.map(u -> recetaRepository.findByUsuario(u)).orElse(Collections.emptyList());
    }

    @Transactional
    public Receta cargarReceta(String nickname, String nombre, String categoria,
                               List<IngredienteDTO> ingredientes, List<PasoDTO> pasos,
                               String descripcion, double porciones,
                               List<MultipartFile> imagenesPasos,
                               MultipartFile imagenReceta) throws Exception {

        Usuario usuario = usuarioRepository.findByNicknameIgnoreCase(nickname)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        TipoReceta tipo = tipoRecetaRepository.findByDescripcionIgnoreCase(categoria)
                .orElseThrow(() -> new Exception("Categoría no encontrada"));

        if (imagenesPasos != null) {
            System.out.println(imagenesPasos.size() + " imagenes de pasos recibidas");
        } else {
            System.out.println("No se recibieron imagenes de pasos");
        }

        // Guarda la receta
        Receta receta = new Receta();
        receta.setUsuario(usuario);
        receta.setNombreReceta(nombre);
        receta.setDescripcionReceta(descripcion);
        receta.setTipoReceta(tipo);
        receta.setPorciones(porciones);

        // Guarda imagen de la receta
        if (imagenReceta != null && !imagenReceta.isEmpty()) {
            String urlImagenReceta = imagenService.upload(imagenReceta, "recetas", receta.getNombreReceta());
            System.out.println("URL de la imagen de la receta: " + urlImagenReceta);
            receta.setFotoPrincipal(urlImagenReceta);
            recetaRepository.save(receta);
            if (urlImagenReceta != null) {
                Foto foto = new Foto();
                foto.setReceta(receta);
                foto.setUrlFoto(urlImagenReceta);
                fotoRepository.save(foto);
            } else {
                System.err.println("No se pudo cargar la imagen de la receta");
            }
        } else {
            System.err.println("No se recibio la imagen de la receta");
        }



        // Guardar ingredientes y su uso
        for (IngredienteDTO dto : ingredientes) {
            Ingrediente ingrediente = ingredienteRepository.findByNombreIgnoreCase(dto.getNombre())
                    .orElseGet(() -> ingredienteRepository.save(new Ingrediente(dto.getNombre())));

            Unidad unidad = unidadRepository.findByDescripcionIgnoreCase(dto.getUnidad())
                    .orElseThrow(() -> new Exception("Unidad no encontrada: " + dto.getUnidad()));

            Utilizado utilizado = new Utilizado(receta, ingrediente, dto.getCantidad(), unidad, dto.getObservaciones());
            utilizadoRepository.save(utilizado);
        }


        // Guardar pasos y asociar imágenes
        for (int i = 0; i < pasos.size(); i++) {
            System.out.println(pasos.size());
            PasoDTO pasoDto = pasos.get(i);
            Paso paso = new Paso(pasoDto.getNroPaso(), pasoDto.getTexto(), receta);
            paso = pasoRepository.save(paso);
            System.out.println("Paso numero " + i + " saved with ID: " + paso.getIdPaso());
            System.out.println("Paso numero " + i + " saved with text: " + paso.getTexto());

            if (imagenesPasos != null &&
                    imagenesPasos.size() > i &&
                    imagenesPasos.get(i) != null &&
                    !imagenesPasos.get(i).isEmpty()) {

                String url = imagenService.upload(imagenesPasos.get(i), "pasos", receta.getNombreReceta());
                Multimedia multimedia = new Multimedia(url, paso);
                System.out.println("Paso ID antes de guardar multimedia: " + paso.getIdPaso());
                multimediaRepository.save(multimedia);
            } else {
                System.out.println("Paso " + i + " no tiene imagen. No se guarda multimedia.");
            }
        }

        return receta;
    }



    @Transactional
    public Receta modificarReceta(Long id, String nickname, String nombre, String categoria,
                                  List<IngredienteDTO> ingredientes, List<PasoDTO> pasos,
                                  String descripcion, MultipartFile[] imagenes) throws Throwable {

        Usuario usuario = usuarioRepository.findByNicknameIgnoreCase(nickname)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        Receta receta = recetaRepository.findByIdReceta(id)
                .orElseThrow(() -> new Exception("Receta no encontrada"));

        //TipoReceta tipo = tipoRecetaRepository.findByDescripcionIgnoreCase(categoria)
        //        .orElseThrow(() -> new Exception("Categoría no encontrada"));

        receta.setDescripcionReceta(descripcion);
        //receta.setTipoReceta(tipo);
        recetaRepository.save(receta);

        // Eliminar ingredientes y pasos anteriores
        utilizadoRepository.deleteByRecetaIdReceta(receta.getIdReceta());

        List<Paso> pasosAnteriores = pasoRepository.findByRecetaIdReceta(receta.getIdReceta());
        for (Paso paso : pasosAnteriores) {
            multimediaRepository.deleteByPasoIdPaso(paso.getIdPaso());
        }
        pasoRepository.deleteByRecetaIdReceta(receta.getIdReceta());

        // Cargar nuevos ingredientes
        for (IngredienteDTO dto : ingredientes) {
            try {
                if (dto.getNombre() == null || dto.getCantidad() == 0.0 || dto.getUnidad() == null) {
                    System.out.println("Ingrediente inválido: " + dto);
                    continue;
                }

                Ingrediente ingrediente = ingredienteRepository.findByNombreIgnoreCase(dto.getNombre())
                        .orElseGet(() -> ingredienteRepository.save(new Ingrediente(dto.getNombre())));

                Unidad unidad = unidadRepository.findByDescripcionIgnoreCase(dto.getUnidad())
                        .orElseThrow(() -> new Exception("Unidad no encontrada: " + dto.getUnidad()));

                Utilizado utilizado = new Utilizado(receta, ingrediente, dto.getCantidad(), unidad, dto.getObservaciones());

                utilizadoRepository.save(utilizado);
                System.out.println("Ingrediente guardado correctamente: " + dto.getNombre());
            } catch (Exception e) {
                System.out.println("Error al guardar ingrediente: " + dto.getNombre() + " - " + e.getMessage());
            }
        }

        // No agregar nuevos pasos si no vienen en la petición
        if (pasos != null && !pasos.isEmpty()) {
            for (int i = 0; i < pasos.size(); i++) {
                PasoDTO pasoDto = pasos.get(i);
                Paso paso = new Paso(pasoDto.getNroPaso(), pasoDto.getTexto(), receta);
                paso = pasoRepository.save(paso);

                if (imagenes != null && i < imagenes.length && !imagenes[i].isEmpty()) {
                    System.out.println("URL de la imagen de paso " + i + ": " + imagenes[i].getOriginalFilename());
                    String url = imagenService.upload(imagenes[i], "pasos", receta.getNombreReceta());
                    Multimedia multimedia = new Multimedia(url, paso);
                    multimediaRepository.save(multimedia);
                }
            }
        }

        return receta;
    }




    public void eliminarReceta(String nombreReceta) throws Throwable {
        Optional<Receta> optionalReceta = recetaRepository.findByNombreRecetaIgnoreCase(nombreReceta);

        if (optionalReceta.isPresent()) {
            Receta receta = optionalReceta.get();
            utilizadoRepository.deleteByRecetaIdReceta(receta.getIdReceta());
            pasoRepository.deleteByRecetaIdReceta(receta.getIdReceta());
            recetaRepository.delete(receta);
        } else {
            throw new EntityNotFoundException("Receta no encontrada con nombre: " + nombreReceta);
        }
    }

    public Receta multiplicarReceta(Long idReceta, double multiplicador) throws Exception {
        Receta receta = recetaRepository.findById(idReceta).orElseThrow(() -> new Exception("Receta no encontrada"));
        List<Utilizado> utilizados = utilizadoRepository.findByRecetaIdReceta(receta.getIdReceta());
        for (Utilizado u : utilizados) {
            u.setCantidad((int) (u.getCantidad() * multiplicador));
            utilizadoRepository.save(u);
        }
        return receta;
    }

    public RecetaDTO obtenerRecetaConPasos(Long idReceta) throws Exception {
        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new Exception("Receta no encontrada"));

        RecetaDTO dto = new RecetaDTO();
        dto.setIdReceta(receta.getIdReceta());
        dto.setNombre(receta.getNombreReceta());
        dto.setDescripcion(receta.getDescripcionReceta());
        dto.setPorciones(receta.getPorciones());
        dto.setCategoria(receta.getTipoReceta().getDescripcion());
        dto.setUrlImagen(receta.getFotoPrincipal());
        dto.setNickname(receta.getUsuario().getNickname());

        List<PasoDTO> pasosDto = pasoRepository.findByRecetaIdReceta(receta.getIdReceta())
                .stream()
                .map(p -> {
                    String url = multimediaRepository.findByPasoIdPaso(p.getIdPaso())
                            .map(Multimedia::getUrlContenido)
                            .orElse("");
                    return new PasoDTO(p.getNroPaso(), p.getTexto(), url);
                })
                .collect(Collectors.toList());
        dto.setPasos(pasosDto);

        List<IngredienteDTO> ingredientesDto = utilizadoRepository.findByRecetaIdReceta(receta.getIdReceta())
                .stream()
                .map(u -> new IngredienteDTO(
                        u.getIngrediente().getNombre(),
                        u.getCantidad(),
                        u.getUnidad().getDescripcion(),
                        u.getObservaciones()
                ))
                .collect(Collectors.toList());
        dto.setIngredientes(ingredientesDto);

        System.out.println("Receta con pasos obtenida: " + dto);

        return dto;
    }

} 

