package dao;

import conexion.Conexion;
import dto.ProductoDTO;
import listas.Lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoDao extends AbstractDao<ProductoDTO> {
    @Override
    public ProductoDTO get(int id) {
        Conexion conexion = Conexion.getInstance();

        String sql = "SELECT * FROM producto WHERE id = " + id;

        ProductoDTO producto = null;

        try {
            conexion.conectar();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(sentencia, sql);

            if (rs.next()) {
                producto = new ProductoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("codigo"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("fechaVencimiento"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }

        return producto;
    }

    public Lista<ProductoDTO> buscar(String criterio) {
        Conexion conexion = Conexion.getInstance();

        String sql = "SELECT * FROM producto ORDER BY nombre ASC";
        if (criterio != null && !criterio.isEmpty()) {
            sql = "SELECT * FROM producto WHERE nombre like '%" + criterio + "%' ORDER BY nombre ASC";
        }

        Lista<ProductoDTO> productos = new Lista<>();

        try {
            conexion.conectar();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(sentencia, sql);

            while (rs.next()) {
                ProductoDTO producto = new ProductoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("codigo"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("fecha_vencimiento"));
                productos.insertar(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }

        return productos;
    }

    public void agregarProducto(ProductoDTO p) {
        Conexion conexion = Conexion.getInstance();
        String sql = "insert into producto values(default, '" +p.nombre() + "', '" + p.codigo() + "', " + p.precio()
                +", " + p.cantidad() + ", '" + p.fechaVencimiento() + "');";

        try {
            conexion.conectar();
            Statement sentencia = conexion.getConexion().createStatement();
            conexion.ejecutar(sentencia,sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conexion.desconectar();
        }

    }

    public void update(ProductoDTO p) {
        Conexion conexion = Conexion.getInstance();

        String sql = "UPDATE producto SET nombre = '" + p.nombre() + "', codigo = '" + p.codigo()+ "', precio = " + p.precio() +
                ", cantidad = " + p.cantidad() + ", fecha_vencimiento = '" + p.fechaVencimiento() + "' WHERE id = " + p.id();
        try {
            conexion.conectar();
            Statement sentencia = conexion.getConexion().createStatement();
            conexion.ejecutar(sentencia, sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    public void eliminarProducto(int id){
        Conexion conexion = Conexion.getInstance();
        String sql = "delete from producto where id = " + id;
        try {
            conexion.conectar();
            Statement sentencia = conexion.getConexion().createStatement();
            conexion.ejecutar(sentencia, sql);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }
}
