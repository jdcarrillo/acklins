/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Juanda
 */
public class Consulta extends javax.swing.JFrame {

    /**
     * Creates new form Consulta
     */
    public Consulta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtWeb = new javax.swing.JTextArea();
        btnOk = new javax.swing.JButton();
        txtCI = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtWeb.setColumns(20);
        txtWeb.setRows(5);
        jScrollPane1.setViewportView(txtWeb);

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        txtCI.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCI, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(btnOk)
                        .addGap(0, 155, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(txtCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed

        String address = "https://declaraciones.sri.gob.ec/consultas-renta-internet/consultaNaturales.jsf?form%3AseleccionRadioButton=C&form%3ArucRazonSocial=&form%3Acedula=1713647640&form%3Apassaport=&form%3AapellidoN=&form%3Asubmit=Buscar&form=form&autoScroll=&javax.faces.ViewState=j_id1";
        String address2 = "https://declaraciones.sri.gob.ec/consultas-renta-internet/consultaNaturales.jsf?AJAXREQUEST=j_id_jsp_1909033050_0&form%3AseleccionRadioButton=C&form%3ArucRazonSocial=&form%3Acedula=&form%3Apassaport=&form%3AapellidoN=&form=form&autoScroll=&javax.faces.ViewState=j_id2&ajaxSingle=form%3AlimpiarDatos&form%3AlimpiarDatos=form%3AlimpiarDatos&";
        //String address="https://declaraciones.sri.gob.ec/consultas-renta-internet/consultaDeudaNaturales.jsf?AJAXREQUEST=j_id_jsp_2048670963_0&form%3AradioTipoPersona=I&form%3Aj_id_jsp_2048670963_7=1713647640&form=form&autoScroll=&javax.faces.ViewState=j_id9&form%3Aj_id_jsp_2048670963_8=form%3Aj_id_jsp_2048670963_8&";
        //String address = "https://declaraciones.sri.gob.ec/facturacion-internet/consultas/publico/ruc-datos1.jspa?pagina=resultado&opcion=1&texto=1713647673001";
        String content = null;
        HttpURLConnection connection = null;

        try {

            connection = (HttpURLConnection) new URL(address).openConnection();
            System.out.println("Metodo: " + connection.getRequestMethod());
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            //SRI x = new SRI();
            txtWeb.setText(content);
            //txtWeb.setText(x.getHTML(address));
            //txtWeb.setText(content);
            //System.out.println(x.getHTML(address));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(content);

        //Extrae extrae = new Extrae();
        //txtWeb.setText(extrae.ExtraeHtml(address));
        //txtWeb.setText(extrae.callURL(address));
        //String r = extrae.ExtraeHtml(address);
        //System.out.println(r);
        Document doc = Jsoup.parse(content);
//        Elements links = doc.select("a[href]");
//        Elements resultLinks = doc.select("h3.r > a");
        //System.out.println(doc);
        //Element masthead = doc.select("tr.par").first();
        //System.out.println(masthead);
        //System.out.println("\n--------------------------------\n");
//        Element link = masthead.select("a").first();
//        String linkText = link.text();
//        System.out.println(linkText);
//        Elements tds = new Elements();
//        for (Element table : doc.select("table.anchoDoc")) { //table.reporte
//            for (Element row : table.select("tr:gt(0)")) {
//                tds = row.select("td:not([rowspan])");
//                System.out.println("Nombres: " + tds.get(1).text() + " -> " + tds.get(0).text());
//
//            }
//
//        }
//        String resultado = null;
//        resultado = "Nombres: " + tds.get(1).text() + " -> " + tds.get(0).text();
//        txtWeb.setText(resultado);
    }//GEN-LAST:event_btnOkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCI;
    private javax.swing.JTextArea txtWeb;
    // End of variables declaration//GEN-END:variables
}
