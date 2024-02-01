package javaminicrud;
/*
*  @author Domingo 2023_02_20
*/
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CStudents {

    private int id;
    private String name;
    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void InsertStudent(JTextField name, JTextField surname) {

        setName(name.getText());
        setSurname(surname.getText());

        CConnection objectConnection = new CConnection();

        String sqlQuery = "insert into Students (name, surname) values (?,?)";

        try {

            CallableStatement cs = objectConnection.establishConnection().prepareCall(sqlQuery);

            cs.setString(1, getName());
            cs.setString(2, getSurname());
            cs.execute();
            JOptionPane.showMessageDialog(null, "The Student Was Inserted Correctly");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Err " + e.toString());

        }
    }

    public void ShowStudents(JTable tableStudents) {

        CConnection objectConnection = new CConnection();

        DefaultTableModel tModel = new DefaultTableModel();

        TableRowSorter<TableModel> sortTable = new TableRowSorter<TableModel>(tModel);

        tableStudents.setRowSorter(sortTable);

        String sqlQuery = "";

        tModel.addColumn("Id");
        tModel.addColumn("Names");
        tModel.addColumn("Surnames");

        tableStudents.setModel(tModel);

        sqlQuery = "select * from Students";

        String[] data = new String[3];
        Statement st;

        try {
            st = objectConnection.establishConnection().createStatement();

            ResultSet rs = st.executeQuery(sqlQuery);

            while (rs.next()) {

                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);

                tModel.addRow(data);
            }

            tableStudents.setModel(tModel);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot display records from database, ERR: " + e.toString());

        }

    }

    public void SelectStudents(JTable tableStudents, JTextField id, JTextField name, JTextField surname) {

        try {

            int rowCounter = tableStudents.getSelectedRow();

            if (rowCounter >= 0) {

                id.setText((String) tableStudents.getValueAt(rowCounter, 0));
                name.setText((String) tableStudents.getValueAt(rowCounter, 1));
                surname.setText((String) tableStudents.getValueAt(rowCounter, 2));

            } else {

                JOptionPane.showMessageDialog(null, "Unselected Row");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Selection Error, " + e.toString());

        }

    }

    public void ModifyStudents(JTextField id, JTextField name, JTextField surname) {

        setId(Integer.parseInt(id.getText()));
        setName(name.getText());
        setSurname(surname.getText());

        CConnection objectConnection = new CConnection();

        String sqlQuery = "UPDATE Students SET Students.name= ?, Students.surname=? WHERE Students.id = ?;";

        try {

            CallableStatement cs = objectConnection.establishConnection().prepareCall(sqlQuery);

            cs.setString(1, getName());
            cs.setString(2, getSurname());
            cs.setInt(3, getId());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Successful Modification");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error Modifying Registry, ERR " + e.toString());

        }
    }

    public void DeleteReg(JTextField id) {

        setId(Integer.parseInt(id.getText()));

        CConnection objectConnection = new CConnection();

        String sqlQuery = "delete from Students where Students.id=?;";

        try {

            CallableStatement cs = objectConnection.establishConnection().prepareCall(sqlQuery);

            cs.setInt(1, getId());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Register Deleted");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Deleted Err, " + e.toString());

        }
    }
}
