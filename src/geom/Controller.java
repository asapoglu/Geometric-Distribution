package geom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import java.lang.Math;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private AnchorPane panel;

    @FXML
    private LineChart<?, ?> birikimliDagilim;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private Button btn_ciz;

    @FXML
    private TextField pid;

    @FXML
    private TextField xid;

    @FXML
    private Label lbl_uyari;

    @FXML
    private ImageView varyans;

    @FXML
    private TextField vary;

    @FXML
    private ImageView mean;

    @FXML
    private TextField mea;

    @FXML
    void handleButtonAction(ActionEvent event) {
        lbl_uyari.setText("");
        String w = pid.getText();
        String v = xid.getText();
        double p = 0;
        int t = 0;
        while (true) {
            try {
                p = Double.valueOf(w);
                break;
            } catch (Exception e) {
                lbl_uyari.setText("Lütfen P değeri için 0 ile 1 arasında bir değer giriniz...");
                return;
            }
        }
        while (true) {
            try {
                t = Integer.valueOf(v);
                if (t < 0) {
                    lbl_uyari.setText("Lütfen X değeri için 0'dan büyük bir değer girin");
                    return;
                }
                break;
            } catch (Exception e) {
                lbl_uyari.setText("Lütfen X değeri için 0'dan büyük bir değer girin");
                return;
            }
        }
        XYChart.Series setData = new XYChart.Series<>();
        setData.setName("PMF = "+w);

        XYChart.Series setData2 = new XYChart.Series<>();
        setData2.setName("CDF = "+w);

        double q = 1 - p;
        int x = 1;
        double k = 0;
        double vr = ((1-p)/Math.pow(p,2));
        vary.setText(String.valueOf(vr));
        double mn = (1/p);
        mea.setText(String.valueOf(mn));

        for (int i = x; i <= t; i++) {
            double l = Math.pow(q, (i - 1));
            double y = p * l;
            k = k + y;

            setData.getData().add(new XYChart.Data(String.valueOf(i), y));
            setData2.getData().add(new XYChart.Data(String.valueOf(i), k));
        }

        birikimliDagilim.getData().addAll(setData);
        birikimliDagilim.getData().addAll(setData2);
    }

}
