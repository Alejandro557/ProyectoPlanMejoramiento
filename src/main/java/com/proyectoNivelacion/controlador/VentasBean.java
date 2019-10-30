/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoNivelacion.controlador;

import com.ibm.icu.text.DateFormat;
import com.proyectoNivelacion.entity.Datospersona;
import com.proyectoNivelacion.entity.Efectivo;
import com.proyectoNivelacion.entity.Pagos;
import com.proyectoNivelacion.entity.Productos;
import com.proyectoNivelacion.entity.ProductosPublicados;
import com.proyectoNivelacion.entity.Tarjetas;
import com.proyectoNivelacion.entity.VentaProductos;
import com.proyectoNivelacion.entity.Ventas;
import com.proyectoNivelacion.helperBean.Mensaje;
import com.proyectoNivelacion.modelo.DatospersonaFacade;
import com.proyectoNivelacion.modelo.EfectivoFacade;
import com.proyectoNivelacion.modelo.PagosFacade;
import com.proyectoNivelacion.modelo.ProductosFacade;
import com.proyectoNivelacion.modelo.ProductosPublicadosFacade;
import com.proyectoNivelacion.modelo.TarjetasFacade;
import com.proyectoNivelacion.modelo.VentaProductosFacade;
import com.proyectoNivelacion.modelo.VentasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Alejandro
 */
@Named(value = "ventasBean")
@SessionScoped
public class VentasBean implements Serializable {

    /**
     * @return the ventasDTO
     */
    public Ventas getVentasDTO() {
        return ventasDTO;
    }

    /**
     * @param ventasDTO the ventasDTO to set
     */
    public void setVentasDTO(Ventas ventasDTO) {
        this.ventasDTO = ventasDTO;
    }

    @EJB
    private ProductosFacade productosDAO;
    private int cantidad = 0;
    @Inject
    private Mensaje menaje;
    private Productos productosDTO = new Productos();
    @EJB
    private ProductosPublicadosFacade productosPublicadosDAO;
    private ProductosPublicados productosPublicadosDTO = new ProductosPublicados();
    @Inject
    private Tirilla tirilla;
    private BigDecimal total = new BigDecimal("0");
    @EJB
    private TarjetasFacade tarjetasDAO;
    private Tarjetas tarjetasDTO = new Tarjetas();
    private int id = 0;
    @EJB
    private VentasFacade ventasDAO;
    private Ventas ventasDTO = new Ventas();
    private Date fechaSistema = new Date(Calendar.getInstance().getTimeInMillis()); // fecha del sistema
    private BigDecimal efectivo = new BigDecimal("0");
    private BigDecimal tarjeta = new BigDecimal("0");
    @EJB
    private VentaProductosFacade ventaProductosDAO;
    private VentaProductos ventaProductoDTO = new VentaProductos();
    @EJB
    private PagosFacade pagosDAO;
    private Pagos pagosDTO = new Pagos();
    @EJB
    private EfectivoFacade efectivoDAO;
    private Efectivo efectivoDTO = new Efectivo();
    private BigDecimal vueltas = new BigDecimal("0");
    @EJB
    private DatospersonaFacade datosPersonaDAO;
    private Datospersona datosPersonaDTO = new Datospersona();

    /**
     * Creates a new instance of VentasBean
     */
    public VentasBean() {
    }

    public void guardarVenta() {
        if (tirilla.getProductos().isEmpty()) {
            menaje.setMensaje("mensaje('Error!', 'Agrega productos a carrito!', 'error');");
        } else {
            if (id != 0) {
                tarjetasDTO = tarjetasDAO.find(id);
                if (tarjetasDTO.getTarjetaDinero().doubleValue() >= tarjeta.doubleValue()) {
                    if (tarjeta.doubleValue() >= total.doubleValue()) {
                        vueltas = new BigDecimal(tarjeta.doubleValue() - total.doubleValue());
                        datosPersonaDAO.create(datosPersonaDTO);
                        ventasDTO.setPersonaId(datosPersonaDTO);
                        ventasDTO.setVentaTotal(total);
                        ventasDTO.setVentaFecha(fechaSistema);
                        ventasDTO.setVentaEstado(true);
                        ventasDTO.setVentaMetodo("TARJETA");
                        ventasDAO.create(ventasDTO);
                        for (ProductosPublicados producto : tirilla.getProductos()) {
                            ventaProductoDTO.setProductoId(producto);
                            ventaProductoDTO.setVentaId(ventasDTO);
                            ventaProductosDAO.create(ventaProductoDTO);
                            ventaProductoDTO = new VentaProductos();
                        }
                        pagosDTO.setTarjetaId(tarjetasDTO);
                        pagosDTO.setVentaId(ventasDTO);
                        pagosDAO.create(pagosDTO);
                        menaje.setMensaje("mensaje('Venta guardada en tarjeta!', 'La venta fue guardada en tarjeta! tienes que dar vueltas al cliente de: $" + vueltas.intValue() + "', 'success');");
                        ventasDTO = new Ventas();
                        efectivoDTO = new Efectivo();
                        pagosDTO = new Pagos();
                        datosPersonaDTO = new Datospersona();
                        tirilla.anular();
                        total = new BigDecimal("0");
                    } else {
                        menaje.setMensaje("mensaje('Error!', 'No ingresaste suficiente dinero para realizar tu compra!', 'error');");
                    }
                } else {
                    menaje.setMensaje("mensaje('Error!', 'No tienes suficiente dinero en tu tarjeta para realizar tu compra!', 'error');");
                }
            } else {
                if (efectivo.doubleValue() >= total.doubleValue()) {
                    vueltas = new BigDecimal(efectivo.doubleValue() - total.doubleValue());
                    datosPersonaDAO.create(datosPersonaDTO);
                    ventasDTO.setPersonaId(datosPersonaDTO);
                    ventasDTO.setVentaTotal(total);
                    ventasDTO.setVentaEstado(true);
                    ventasDTO.setVentaFecha(fechaSistema);
                    ventasDTO.setVentaMetodo("EFECTIVO");
                    ventasDAO.create(ventasDTO);
                    for (ProductosPublicados producto : tirilla.getProductos()) {
                        ventaProductoDTO.setProductoId(producto);
                        ventaProductoDTO.setVentaId(ventasDTO);
                        ventaProductosDAO.create(ventaProductoDTO);
                        ventaProductoDTO = new VentaProductos();
                    }
                    efectivoDTO.setEfectivoDinero(efectivo);
                    efectivoDTO.setEfectivoVueltas(vueltas);
                    efectivoDAO.create(efectivoDTO);
                    pagosDTO.setEfectivoId(efectivoDTO);
                    pagosDTO.setVentaId(ventasDTO);
                    pagosDAO.create(pagosDTO);
                    menaje.setMensaje("mensaje('Venta guardada en efectivo!', 'La venta fue guardada en efectivo! tienes que dar vueltas al cliente de: $" + vueltas.intValue() + "', 'success');");
                    tirilla.anular();
                    ventasDTO = new Ventas();
                    datosPersonaDTO = new Datospersona();
                    efectivoDTO = new Efectivo();
                    pagosDTO = new Pagos();
                    total = new BigDecimal("0");
                } else {
                    menaje.setMensaje("mensaje('Error!', 'No tienes suficiente dinero en efectivo para realizar tu compra!', 'error');");
                }
            }
        }
    }

    public void cancelarVenta() {
        datosPersonaDTO = new Datospersona();
        efectivo = new BigDecimal("0");
        tarjeta = new BigDecimal("0");
    }

    public List<SelectItem> itemsTarjeta() {
        List<SelectItem> items = new ArrayList<>();
        for (Tarjetas tarjetas : tarjetasDAO.findAllTarjetasForEstadoTrue()) {
            SelectItem item = new SelectItem(tarjetas.getTarjetaId(), tarjetas.getTarjetaNombre() + " No Documento: " + tarjetas.getPersonaId().getPersonaDocumento());
            items.add(item);
        }
        return items;
    }

    public void anularVenta() {
        tirilla.anular();
        menaje.setMensaje("mensaje('Anulado!', 'Venta anulada!', 'success');");
        total = new BigDecimal("0");
    }

    public void eliminarProducto(ProductosPublicados producto) {
        tirilla.eliminarProducto(producto);
        total = tirilla.total();
    }

    public void agregarProductoATirilla(ProductosPublicados producto) {
        tirilla.agregarProducto(producto);
        total = tirilla.total();
        menaje.setMensaje("mensaje('Agregado!', 'Producto agregado a la tirilla!', 'success');");
    }

    public void venderProducto(Productos producto) {
        if (cantidad > 0) {
            if (producto.getProductoCantidad() >= cantidad) {
                productosPublicadosDTO.setCantidad(cantidad);
                productosPublicadosDTO.setProductoId(producto);
                productosPublicadosDAO.create(productosPublicadosDTO);
                int total = producto.getProductoCantidad() - cantidad;
                producto.setProductoCantidad(total);
                total = 0;
                productosDAO.edit(producto);
                menaje.setMensaje("mensaje('Vendido!', 'Producto a la venta!', 'success');");
            } else {
                menaje.setMensaje("mensaje('Error!', 'No tienes existencias disponibles!', 'error');");
            }
        } else {
            menaje.setMensaje("mensaje('Error!', 'La cantidad tienen que ser mayor a 0!', 'error');");
        }
        cantidad = 0;
        productosPublicadosDTO = new ProductosPublicados();
    }

    public List<Productos> productosSinVender() {
        return productosDAO.findAllProductosForEstadoTrue();
    }

    public List<ProductosPublicados> productosVendidos() {
        return productosPublicadosDAO.findAll();
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the productosDTO
     */
    public Productos getProductosDTO() {
        return productosDTO;
    }

    /**
     * @param productosDTO the productosDTO to set
     */
    public void setProductosDTO(Productos productosDTO) {
        this.productosDTO = productosDTO;
    }

    /**
     * @return the productosPublicadosDTO
     */
    public ProductosPublicados getProductosPublicadosDTO() {
        return productosPublicadosDTO;
    }

    /**
     * @param productosPublicadosDTO the productosPublicadosDTO to set
     */
    public void setProductosPublicadosDTO(ProductosPublicados productosPublicadosDTO) {
        this.productosPublicadosDTO = productosPublicadosDTO;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the tarjetasDTO
     */
    public Tarjetas getTarjetasDTO() {
        return tarjetasDTO;
    }

    /**
     * @param tarjetasDTO the tarjetasDTO to set
     */
    public void setTarjetasDTO(Tarjetas tarjetasDTO) {
        this.tarjetasDTO = tarjetasDTO;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the efectivo
     */
    public BigDecimal getEfectivo() {
        return efectivo;
    }

    /**
     * @param efectivo the efectivo to set
     */
    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    /**
     * @return the tarjeta
     */
    public BigDecimal getTarjeta() {
        return tarjeta;
    }

    /**
     * @param tarjeta the tarjeta to set
     */
    public void setTarjeta(BigDecimal tarjeta) {
        this.tarjeta = tarjeta;
    }

    /**
     * @return the datosPersonaDTO
     */
    public Datospersona getDatosPersonaDTO() {
        return datosPersonaDTO;
    }

    /**
     * @param datosPersonaDTO the datosPersonaDTO to set
     */
    public void setDatosPersonaDTO(Datospersona datosPersonaDTO) {
        this.datosPersonaDTO = datosPersonaDTO;
    }

}
