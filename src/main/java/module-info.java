module vdcb.psychoexpert {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.pool2;

    opens vdcb.psychoexpert to javafx.fxml;
    exports vdcb.psychoexpert;
}
