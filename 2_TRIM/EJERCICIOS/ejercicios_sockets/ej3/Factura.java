import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")
public class Factura implements Serializable{
    private String numeroFactura;
    private LocalDate fechaFactura;
    private double importeFactura;
    private TipoIVA tipoIVA;

    private int IVA;
    private double importeTotal;
    public Factura(String numeroFactura, LocalDate fechaFactura, double importeFactura, TipoIVA tipoIVA ) {
        super();
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
        this.importeFactura = importeFactura;
        this.tipoIVA = tipoIVA;
    }
    public String getNumeroFactura() {
        return numeroFactura;
    }
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public LocalDate getFechaFactura() {
        return fechaFactura;
    }
    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
    public double getImporteFactura() {
        return importeFactura;
    }
    public void setImporteFactura(double importeFactura) {
        this.importeFactura = importeFactura;
    }
    public TipoIVA getTipoIVA() {
        return tipoIVA;
    }
    public void setTipoIVA(TipoIVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }
    
    public int getIVA()
    {
        if(tipoIVA == TipoIVA.IGC)
        {
            IVA = 7;
            return this.IVA;
            
        }
        else if(tipoIVA == TipoIVA.ESP)
        {
            IVA = 21;
            return this.IVA;
        }
        else if (tipoIVA == TipoIVA.EUR)
        {
            IVA = 15;
            return this.IVA;
        }
        else
        {
            throw new IllegalArgumentException("Tipo de IVA no reconocido...");
        }
    }
    public double getImporteTotal()
    {
        double importeIva = importeFactura * (getIVA() / 100.0);
        importeTotal = importeFactura + importeIva;
        return this.importeTotal;
    }
}
enum TipoIVA{
    IGC, ESP, EUR
}
