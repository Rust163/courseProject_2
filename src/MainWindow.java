import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
public class MainWindow {
    public static void main(String[] args) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        /*узнаем размер экрана,
          возвращает пару значений
          длина-ширина*/

        JFrame main_frame = new JFrame("Еженедельник");

        //новое окно с заголовком "new window"

        main_frame.setVisible(true);
        main_frame.getJMenuBar();
        //метод позволяет визуализировать окно (если флаг true)
        JButton b2 = new JButton();
        b2 = new JButton("Средняя кнопка");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_M);

        int frame_width = 1500, frame_height = 800;

        main_frame.setBounds((size.width - frame_width) /2,
                (size.height - frame_height)/2,
                frame_width, frame_height);

        /*задает положение окна на экране
           первые два параметра отвечают за
           расположение окна по горизонтали и
           вертикали, а вторые за размеры самого  окна*/

        main_frame.setResizable(false);
        /*метод фиксирует размер окна, то есть блокирует изменение его размера*/
    }
}
