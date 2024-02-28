package dto;

import java.util.Date;

public record ProductoDTO(int id, String nombre, String codigo, double precio, int cantidad, String fechaVencimiento) {
}
