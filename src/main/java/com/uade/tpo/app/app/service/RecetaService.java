package com.uade.tpo.app.app.service;

import com.uade.tpo.app.app.model.*;
import com.uade.tpo.app.app.dto.*;
import com.uade.tpo.app.app.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private ImagenService imagenService;

    public List<Receta> listarRecetas() {
        return recetaRepository.findAll();
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

    public Receta cargarReceta(String nickname, String nombre, String categoria,
                               List<IngredienteDTO> ingredientes, List<PasoDTO> pasos,
                               String descripcion, MultipartFile[] imagenes) throws Exception {

//        Usuario usuario = usuarioRepository.findByNicknameIgnoreCase(nickname)
//                .orElseThrow(() -> new Exception("Usuario no encontrado"));
//
//        TipoReceta tipo = tipoRecetaRepository.findByDescripcionIgnoreCase(categoria)
//                .orElseThrow(() -> new Exception("Categoría no encontrada"));

        Receta receta = new Receta();
        //receta.setUsuario(usuario);
        receta.setNombreReceta(nombre);
        receta.setDescripcionReceta(descripcion);
        //receta.setTipoReceta(tipo);
        recetaRepository.save(receta);

        System.out.println("Ingredientes recibidos:");
        for (IngredienteDTO i : ingredientes) {
            System.out.println(i.getNombre() + " - " + i.getCantidad() + " " + i.getUnidad());
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
            PasoDTO pasoDto = pasos.get(i);
            Paso paso = new Paso(pasoDto.getNroPaso(), pasoDto.getTexto(), receta);
            paso = pasoRepository.save(paso);

            if (imagenes != null && i < imagenes.length && !imagenes[i].isEmpty()) {
                String url = imagenService.upload(imagenes[i], "pasos", receta.getNombreReceta());
                Multimedia multimedia = new Multimedia(url, paso);
                multimediaRepository.save(multimedia);
            }
        }

        return receta;
    }




    public Receta modificarReceta(String nickname, String nombre, String categoria,
                                  List<IngredienteDTO> ingredientes, List<PasoDTO> pasos,
                                  String descripcion, MultipartFile[] imagenes) throws Throwable {

        Usuario usuario = usuarioRepository.findByNicknameIgnoreCase(nickname)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        Receta receta = recetaRepository.findByNombreRecetaIgnoreCase(nombre)
                .orElseThrow(() -> new Exception("Receta no encontrada"));

        TipoReceta tipo = tipoRecetaRepository.findByDescripcionIgnoreCase(categoria)
                .orElseThrow(() -> new Exception("Categoría no encontrada"));

        receta.setDescripcionReceta(descripcion);
        receta.setTipoReceta(tipo);
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
            Ingrediente ingrediente = ingredienteRepository.findByNombreIgnoreCase(dto.getNombre())
                    .orElseGet(() -> ingredienteRepository.save(new Ingrediente(dto.getNombre())));

            Utilizado utilizado = new Utilizado(receta, ingrediente, dto.getCantidad(), dto.getUnidad(), dto.getObservaciones());
            utilizadoRepository.save(utilizado);
        }

        // Cargar nuevos pasos y sus imágenes
        for (int i = 0; i < pasos.size(); i++) {
            PasoDTO pasoDto = pasos.get(i);
            Paso paso = new Paso(pasoDto.getNroPaso(), pasoDto.getTexto(), receta);
            paso = pasoRepository.save(paso);

            if (imagenes != null && i < imagenes.length && !imagenes[i].isEmpty()) {
                String url = imagenService.upload(imagenes[i], "pasos", receta.getNombreReceta());
                Multimedia multimedia = new Multimedia(url, paso);
                multimediaRepository.save(multimedia);
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
        dto.setId(receta.getIdReceta());
        dto.setNombre(receta.getNombreReceta());
        dto.setDescripcion(receta.getDescripcionReceta());
        //dto.setCategoria(receta.getTipoReceta().getDescripcion());

        List<PasoDTO> pasosDto = pasoRepository.findByRecetaIdReceta(receta.getIdReceta())
                .stream()
                .map(p -> {
                    return new PasoDTO(p.getNroPaso(), p.getTexto(), p.geturlImagen());
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

        return dto;
    }

} 

