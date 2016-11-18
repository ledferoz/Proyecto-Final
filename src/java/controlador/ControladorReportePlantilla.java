/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entitis.Plantillas;
import facade.PlantillasFacade;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 *
 * @author Ferchito
 */
@Named(value = "controladorReportePlantilla")
@RequestScoped
public class ControladorReportePlantilla {

    @EJB
    private PlantillasFacade plantillasfacade;
    private List<Plantillas> plantillas;
    

    @PostConstruct
    public void init() {
        plantillas = plantillasfacade.findAll();
    }

    /**
     * Creates a new instance of ControladorReporteUsuario
     */
    public void exportarPDF() throws JRException, IOException {
        //Exportacion a PDF
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("generado Por", "ISQM Team");
        String path = fc.getExternalContext().getRealPath("./reportes/reportPlantillas.jasper");
        File archivo = new File(path);
        JasperPrint jasper = JasperFillManager.fillReport(archivo.getPath(), parametros, new JRBeanCollectionDataSource(plantillas));
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setHeader("Content-disposition", "attachment;filename=reportedeplantillas-" + new Date() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasper, stream);
        stream.flush();
        stream.close();
        fc.responseComplete();

    }
    
    public void exportarXLS() throws JRException, IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("generado Por", "ISQM Team");
        String path = fc.getExternalContext().getRealPath("./reportes/reportPlantillas.jasper");
        File archivo = new File(path);
        JasperPrint jasper = JasperFillManager.fillReport(archivo.getPath(), parametros, new JRBeanCollectionDataSource(plantillas));
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setHeader("Content-disposition", "attachment;filename=reportedePlantillas-" + new Date() + ".xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        //JasperExportManager.exportReportToPdfStream(jasper, stream);
        stream.flush();
        stream.close();
        fc.responseComplete();

    }
}
