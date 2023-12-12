package gui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FrmTroGiup extends JFrame {

    private JEditorPane editorPane;

    public FrmTroGiup() {
        initComponents();
    }

    private void initComponents() {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");

        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Thiết lập HyperlinkListener để xử lý sự kiện khi liên kết được nhấp
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    openWebPage(e.getURL());
                }
            }
        });

        JButton helpButton = new JButton("Hãy Chọn Tôi Để Biết Thêm Thông Tin");
        helpButton.addActionListener(e -> showHelpPage());

        add(helpButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void openWebPage(URL url) {
		// TODO Auto-generated method stub
		
	}

	private void showHelpPage() {
        // Thay thế HTML dưới đây bằng nội dung thực tế của trang trợ giúp
        String htmlContent = "<html><body><h1>Có Thể Bạn Cần Biết</h1>"
                + "Cung cấp thông tin hữu ích cho người dùng.<h2>Hướng dẫn mua hàng:</h2>\r\nKhách hàng chọn sản phẩm và bỏ vào giỏ hàng mua đến quầy thanh toán, nhân viên tiến hành thanh toán bằng cách nhập tên của sản phẩm sau đó điền đủ các thông tin cần thiết để xuất hóa đơn."
                + "<h2>Hướng dẫn chọn màu:</h2> Nhân viên tư vấn khách hàng tùy theo màu da và sở thích của khách hàng. Ví dụ:<li>*Khách có màu da trắng: nhân viên nên tư vấn cho khách các gam màu như đỏ, đen, tím,... Bên cạnh đó vẫn nên chú ý về body của khách để tiến hành tư vấn chính xác và phù hợp.</li><li>*Khách có màu da sẫm: nhân viên nên tư vấn cho khách các gam màu nhẹ nhàng như trắng, hồng pastel,... Nên tham khảo thêm ý kiến của khách hàng để tư vấn đúng với ý thích của khách hàng.</li><li>*Với các khách hàng có sở thích là các gam màu nóng thì nhân viên nên chú ý vào cách phối đồ để làm hài hòa quần áo và phù hợp với khách hàng.</li>"
                +"<h2>Hướng dẫn chọn size:</h2>Tùy vào hình thể chính xác của khách hàng mà tiến hành tư vấn size cho phù hợp nhưng bên cạnh đó vẫn nên chu ý vào việc hạn chế các khuyết điểm của khách hàng."
                +"<h2>Chính sách đổi trả:</h2>Nhân viên tiến hành kiểm tra hiện trạng sản phẩm và thời gian trên hóa đơn. Nếu không hư hỏng và hóa đơn còn hạn trong 7 ngày thì tiến hành đổi trả và ngược lại.</p>"
                + "<p>Xem thêm tại <a href='https://iuhedu-my.sharepoint.com/:w:/g/personal/21023391_sang_student_iuh_edu_vn/ERWdNyL5DhFHpvG971v8L3sBEBhMjw1EGOM1FIJ6R5binQ?e=kfv4Qi'>https://iuhedu-my.sharepoint.com/:w:/g/personal/21023391_sang_student_iuh_edu_vn/ERWdNyL5DhFHpvG971v8L3sBEBhMjw1EGOM1FIJ6R5binQ?e=kfv4Qi</a></p></body></html></p>";

        editorPane.setText(htmlContent);
    }

    private void openWebPage(URI uri) {
        try {
            Desktop.getDesktop().browse(uri);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmTroGiup());
    }
}
