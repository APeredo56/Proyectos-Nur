import Autos.Estacionamientos.EstacionamientoCompacto;
import Autos.Estacionamientos.EstacionamientoGrandes;
import Autos.Estacionamientos.EstacionamientoNormales;
import Autos.Estacionamientos.EstacionamientoVIP;
import Autos.Models.Auto;
import Autos.Models.Persona;
import Inventario.Models.Inventario;
import Inventario.Models.OrdenCompra;
import Inventario.Models.OrdenVenta;
import Inventario.Models.Producto;

public class Main {
    public static void main(String[] args) {
        //Estacionamientos
        Persona condVip = new Persona("Juan", "Vip", true);
        Persona condGrande = new Persona("Juan", "Grande", false);
        Persona condNormal = new Persona("Juan", "Normal", false);

        EstacionamientoVIP handlerVIP = new EstacionamientoVIP(null);
        EstacionamientoGrandes handlerGrandes = new EstacionamientoGrandes(handlerVIP);
        EstacionamientoNormales handlerNormales = new EstacionamientoNormales(handlerGrandes);
        EstacionamientoCompacto handlerCompactos = new EstacionamientoCompacto(handlerNormales);

        Auto autoVIP = new Auto("ASD123", 7,7,7,condVip);
        Auto autoGrande = new Auto("ASD124", 2.6, 3.2, 6.5, condGrande);
        Auto autoNormal = new Auto("ASD125", 2.4, 2.9, 5.8, condNormal);

        handlerCompactos.handle(autoVIP);
        handlerCompactos.handle(autoGrande);
        handlerCompactos.handle(autoNormal);

        //Inventario
        Inventario inventario = Inventario.getInstance();
        Producto producto1 = new Producto("Laptop", "lap", 1200, 3);
        Producto producto2 = new Producto("Mouse", "mou", 40, 5);

        OrdenCompra ordenCompra = new OrdenCompra(producto1);
        OrdenVenta ordenVenta = new OrdenVenta(producto1);

        OrdenCompra ordenCompra2 = new OrdenCompra(producto2);
        OrdenVenta ordenVenta2 = new OrdenVenta(producto2);


        inventario.addSubscriber(ordenCompra);
        inventario.addSubscriber(ordenVenta);
        inventario.addSubscriber(ordenCompra2);
        inventario.addSubscriber(ordenVenta2);

        ordenCompra.confirmar();
        ordenCompra2.confirmar();
        ordenVenta.confirmar();
        ordenVenta2.confirmar();
    }
}