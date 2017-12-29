package cellulanttest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ernest
 * 
 * NAME: Consume Form
 * 
 * Description: Client form, where the user intiates the process by click event on the jButton1
 * 
 * DataFlow:
 * 1) Click of button
 * 2) Select from practical table and get the Miles and LengthID
 * 3) Within Loop, pass the Miles from table and consume WSDL
 * 4) Update the practical table with the Kilometers and dateModified values
 * 5) Export to PDF
 * 
 * 
 */
public class Consume extends javax.swing.JFrame {

    /**
     * Creates new form Consume
     */
    public Consume() {
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cellulant");
        setLocation(new java.awt.Point(4, 0));
        setResizable(false);

        jButton1.setText("Create PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Cellulant Practical Ernest Muroiwa 0774002797");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
       //Start Creating Objects of classes
       ConnectionManager mysqlConnect = new ConnectionManager();
       Calculations calcObject = new Calculations();
       dbFunction dbFunctionObject = new dbFunction();
       WriteLog writeLogObject=new WriteLog();
       ExportPDF exportObject = new ExportPDF();
    
       
       //Start Get Table data and manipulate
      String sql = "SELECT * FROM `practical`";
    try 
{
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
  
    while (rs.next()) 
    {
            double Miles = rs.getDouble("Miles");
            double originalKiloMeters = rs.getDouble("Kilometers");
            int LengthID = rs.getInt("LengthID");
            //Convert data
            double kiloMeters=calcObject.ConvertData(Miles);

            //Update Table with convert data
            dbFunctionObject.UpdateTable(LengthID,kiloMeters );

            //Write to log
            writeLogObject.LogNow("Update Operation: Table=practical | Values Changed="+ originalKiloMeters + " To" + kiloMeters + " Where LengthID="+LengthID  , "");
                    }

}
     catch (SQLException e) 
{
             e.printStackTrace();
            //Write to log
            writeLogObject.LogNow("Error:" +  e.getMessage() , "severe");

} 
    finally 
{
             mysqlConnect.disconnect();
}

//export to PDF
exportObject.Export();
//Write to log
writeLogObject.LogNow("Information: Export to PDF Was Completed Successfully", "");

//UI MessageBox
       JOptionPane.showMessageDialog(null,"Proccess Was Completed Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
       
//Write to log
writeLogObject.LogNow("Information: Proccess Was Completed Successfully", "");
    }//GEN-LAST:event_jButton1ActionPerformed
     //End Get Table data and manipulate
    
    
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
            java.util.logging.Logger.getLogger(Consume.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consume.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consume.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consume.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consume().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
public void callAsyncCallback(String text){
                 
}

}
