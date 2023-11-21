import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import model.*;
import org.eclipse.persistence.internal.sessions.DirectCollectionChangeRecord;

import javax.sound.sampled.Line;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static java.time.chrono.JapaneseEra.values;

public class test {
    public static void main(String[] args) throws ParseException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
        Category c = new Category();
        c.setCategoryID("CATE1");
        c.setCategoryName("Science");
//        entityManager.persist(c);

        Category c1 = new Category();
        c1.setCategoryID("CATE2");
        c1.setCategoryName("Comic");
//        entityManager.persist(c1);

        Category c2 = new Category();
        c2.setCategoryID("CATE3");
        c2.setCategoryName("Novel");
//        entityManager.persist(c2);

        Category c3 = new Category();
        c3.setCategoryID("CATE4");
        c3.setCategoryName("Thriller");
//        entityManager.persist(c3);

        Category c4 = new Category();
        c4.setCategoryID("CATE5");
        c4.setCategoryName("Dictionary");
//        entityManager.persist(c4);

        Publisher p = new Publisher();
        p.setPublisherID("PUBSH1");
        p.setPublisherName("Kim Đồng");
//        entityManager.persist(p);

        Publisher p1 = new Publisher();
        p1.setPublisherID("PUBSH2");
        p1.setPublisherName("Trẻ");
//        entityManager.persist(p1);

        Publisher p2 = new Publisher();
        p2.setPublisherID("PUBSH3");
        p2.setPublisherName("Hà Nội");
//        entityManager.persist(p2);

        Publisher p3 = new Publisher();
        p3.setPublisherID("PUBSH4");
        p3.setPublisherName("Oxford");
//        entityManager.persist(p3);

        Author a = new Author();
        a.setAuthorID("AUTH1");
        a.setAuthorName("Fujiko Fujio");
//        entityManager.persist(a);

        Author a1 = new Author();
        a1.setAuthorID("AUTH2");
        a1.setAuthorName("Nguyễn Nhật Ánh");
//        entityManager.persist(a1);

        Author a2 = new Author();
        a2.setAuthorID("AUTH3");
        a2.setAuthorName("Sherlock Holmes");
//        entityManager.persist(a2);

        Author a3 = new Author();
        a3.setAuthorID("AUTH4");
        a3.setAuthorName("Higashino Keigo");
//        entityManager.persist(a3);

        Author a4 = new Author();
        a4.setAuthorID("AUTH5");
        a4.setAuthorName("John Simpson");
//        entityManager.persist(a4);

        Author a5 = new Author();
        a5.setAuthorID("AUTH6");
        a5.setAuthorName("Henry Sweet");
//        entityManager.persist(a5);

        Book b = new Book();
        b.setBookID("BOOK1");
        b.setBookName("Doraemon Vol.1");
        b.setLanguage("Tiếng Việt");
        b.setPrice(20000);
        b.setPublisherYear(dateFormat.parse("2020-05-14"));
        //b.setDescription("“Chú khủng long của Nobita” là tác phẩm mở đầu cho xêri Doraemon truyện dài, và đã được chuyển thể thành phim hoạt hình. Trong tác phẩm này, 5 người nhóm bạn Doraemon đã ngược dòng thời gian, trở về thế giới khủng long Kỉ Bạch Á. Tất cả đã sát cánh bên nhau trải qua bao nhiêu sóng gió hiểm nguy để bảo vệ chú khủng long mới nở Pisuke.");
        b.setPublisher(p);
        b.setCategory(c1);
        Collection<Author> auth = new ArrayList<>();
        auth.add(a);
        entityManager.merge(b);
        Author au = entityManager.find(Author.class,"AUTH1");
        Book bk = entityManager.find(Book.class, "BOOK1");
        bk.getAuthor().add(au);
        au.getBook().add(bk);

        Book b1 = new Book();
        b1.setBookID("BOOK2");
        b1.setBookName("Doraemon Vol.2");
        b1.setLanguage("Tiếng Việt");
        b1.setPrice(20000);
        b1.setPublisherYear(dateFormat.parse("2020-09-20"));
        //b1.setDescription("“Nobita và lịch sử khai phá vũ trụ” là tác phẩm thứ 2 trong xêri Doraemon truyện dài, và cũng đã được chuyển thể thành phim hoạt hình. Ở trên trái đất, Nobita hậu đậu làm việc gì cũng thất bại, nhưng khi đến hành tinh Koyakoya nơi lực hút rất yếu thì cậu bỗng trở nên mạnh mẽ phi thường. Bằng sức mạnh ấy, Nobita đã chiến đấu với bọn người xấu để bảo vệ hòa bình cho hành tinh này.");
        b1.setPublisher(p);
        b1.setCategory(c1);
        Collection<Author> auth1 = new ArrayList<>();
        auth1.add(a);
        entityManager.merge(b1);
        Author au2 = entityManager.find(Author.class,"AUTH1");
        Book bk2 = entityManager.find(Book.class, "BOOK2");
        bk2.getAuthor().add(au2);
        au2.getBook().add(bk2);

        Book b2 = new Book();
        b2.setBookID("BOOK3");
        b2.setBookName("Án Mạng Mười Một Chữ");
        b2.setLanguage("Tiếng Việt");
        b2.setPrice(20000);
        b2.setPublisherYear(dateFormat.parse("2020-03-12"));
        //b2.setDescription("Tình cờ phát hiện những điều bất thường sau cái chết thảm khốc của người yêu, nhân vật “tôi”, một nữ nhà văn viết tiểu thuyết trinh thám đã cùng bạn mình, Hagio Fuyuko, cũng là biên tập viên phụ trách sách của “tôi” quyết định điều tra về cái chết này. Trong quá trình điều tra hai người phát hiện người yêu của “tôi” đã từng gặp tai nạn lật thuyền trong chuyến du lịch đảo một năm trước. Và khi họ tìm tới những người cũng tham gia chuyến đi đó để tìm hiểu thì những người này cũng lần lượt bị sát hại. Cuối cùng “tôi” buộc phải tự mình phán đoán, điều tra để tìm ra chân tướng sự việc.");
        b2.setPublisher(p2);
        b2.setCategory(c2);
        Collection<Author> auth2 = new ArrayList<>();
        auth2.add(a3);
        entityManager.merge(b2);
        Author au3 = entityManager.find(Author.class,"AUTH4");
        Book bk3 = entityManager.find(Book.class, "BOOK3");
        bk3.getAuthor().add(au3);
        au3.getBook().add(bk3);

        Book b3 = new Book();
        b3.setBookID("BOOK4");
        b3.setBookName("Oxford English Dictionary");
        b3.setLanguage("Tiếng Anh");
        b3.setPrice(200000);
        b3.setPublisherYear(dateFormat.parse("1884-2-1"));
        //b3.setDescription("Cuốn từ điển đặc biệt phù hợp với sinh viên và người học tiếng Anh, thể hiện ở những mục từ mới mẻ, cập nhật, những lời giải thích ngắn gọn mà rõ ràng, kèm với những ví dụ cụ thể mà sinh động, những mẫu câu, kết cấu phổ dụng mà đa dạng của mỗi mục từ nhằm giúp người đọc biết cách sử dụng từ ngữ chính xác, phù hợp. Hiện nay, khá nhiều người học chỉ sử dụng loại từ điển “bỏ túi”. Dĩ nhiên, loại từ điển ấy có lợi thế là gọn nhẹ và tiện dụng, nhưng nếu muốn tìm hiểu sâu về từ ngữ thì những từ điển ấy không đáp ứng được, vì trong đó chỉ một vài nghĩa cơ bản nhất của mục từ được dịch sang tiếng Việt mà không gắn với ngữ cảnh, hoặc thông tin cú pháp cũng như ngữ dụng của mục từ không được cung cấp một cách đầy đủ. Do vậy, cho dù người học có hiểu nghĩa cũng khó có thể biết cách sử dụng từ cho đúng, trong khi mục tiêu cao nhất của việc dạy – học ngoại ngữ là làm sao cho người học sử dụng được ngôn ngữ đích. Việc thể hiện mục tiêu đó trong từng mục từ của cuốn từ điển này là điểm đặc biệt nổi bật của công trình so với nhiều ấn phẩm cùng loại khác. Thêm vào đó, chúng ta gặp khá nhiều vấn đề khi học từ vựng tiếng Anh như: - Ngồi tra từ điển online làm mất tập trung trong khi học - Bạn tra nghĩa trên mạng nên nghĩa và phiên âm mỗi chỗ một kiểu - Dịch nghĩa tiếng Anh trực tiếp sang tiếng Việt nhiều khi bị sai nghĩa của từ - Không nắm được đầy đủ ý nghĩa của từ (bởi một từ có nhiều nghĩa khác nhau). Tất cả những khó khăn trên đều được giải quyết với cuốn ‘Từ điển Anh – Anh – Việt” Đây là cuốn từ điển được biên dịch dựa theo cuốn từ điển Oxford là một công trình liên tục được đổi mới và công bố bởi một nhà xuất bản uy tín trên thế giới với rất nhiều ấn phẩm khác nhau đã có mặt tại Việt Nam, trợ giúp cho các nhà nghiên cứu, giảng viên và đặc biệt là sinh viên Việt Nam nhiều thập kỷ qua. Còn The Windy là nhóm tác giả đã quá quen thuộc với những người Việt học tiếng Anh, những đầu sách học tiếng Anh của The Windy đã giúp cho hàng triệu người Việt Nam có thể thành thạo môn ngoại ngữ này. Hãy cùng chúng tôi điểm qua những ưu điểm nổi bật của cuốn sách này: - Từ điển bìa cứng giúp bạn tra cứu dễ dàng và giữ sách bền, đẹp - Cuốn từ điển gồm 350.000 mục lục được chọn lọc dựa trên phiên bản mới nhất của Oxford và Cambride, đã lược bỏ một số từ cổ mà người Anh – Mỹ ít dùng. - Các từ vựng đều được  cập nhật phiên âm mới, chính xác nhất. - Bổ sung thêm 85 phụ lục – Từ điển tranh đặc sắc chia theo nhiều chủ đề: Nông thôn – thành thị – vùng núi – động vật – các tòa nhà – sân vườn – nấu ăn,… tiện dụng và dễ dàng tra cứu. Mã hàng\t8935246917978 Tên Nhà Cung Cấp\tMCBooks Tác giả\tThe Windy NXB\tNXB Đại Học Quốc Gia Hà Nội Năm XB\t2018 Ngôn Ngữ\tSong Ngữ Anh - Việt Trọng lượng (gr)\t1800 Kích Thước Bao Bì\t11.5 x 17.5 cm Số trang\t1570 Hình thức\tBìa Cứng Sản phẩm hiển thị trong MCBooks Sản phẩm bán chạy nhất\tTop 100 sản phẩm Từ điển tiếng Anh-Anh, Anh-Việt, Việt Anh bán chạy của tháng Giá sản phẩm trên Fahasa.com đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như Phụ phí đóng gói, phí vận chuyển, phụ phí hàng cồng kềnh,... Chính sách khuyến mãi trên Fahasa.com không áp dụng cho Hệ thống Nhà sách Fahasa trên toàn quốc Từ Điển Oxford Anh - Anh - Việt (Bìa Vàng) - Tái Bản Cuốn từ điển đặc biệt phù hợp với sinh viên và người học tiếng Anh, thể hiện ở những mục từ mới mẻ, cập nhật, những lời giải thích ngắn gọn mà rõ ràng, kèm với những ví dụ cụ thể mà sinh động, những mẫu câu, kết cấu phổ dụng mà đa dạng của mỗi mục từ nhằm giúp người đọc biết cách sử dụng từ ngữ chính xác, phù hợp.");
        b3.setPublisher(p3);
        b3.setCategory(c4);
        Collection<Author> auth3 = new ArrayList<>();
        auth3.add(a4);
        auth3.add(a5);
        entityManager.merge(b3);
        Author au4 = entityManager.find(Author.class,"AUTH5");
        Author au5 = entityManager.find(Author.class,"AUTH6");
        Book bk4 = entityManager.find(Book.class, "BOOK4");
        bk4.getAuthor().add(au4);
        bk4.getAuthor().add(au5);
        au4.getBook().add(bk4);
        au5.getBook().add(bk4);

        Book b4 = new Book();
        b4.setBookID("BOOK5");
        b4.setBookName("Tôi thấy hoa vàng trên cỏ xanh");
        b4.setLanguage("Tiếng Việt");
        b4.setPrice(90000);
        b4.setPublisherYear(dateFormat.parse("2018-12-28"));
        //b4.setDescription("Cuốn sách viết về tuổi thơ nghèo khó ở một làng quê, bên cạnh đề tài tình yêu quen thuộc, lần đầu tiên Nguyễn Nhật Ánh đưa vào tác phẩm của mình những nhân vật phản diện và đặt ra vấn đề đạo đức như sự vô tâm, cái ác. 81 chương ngắn là 81 câu chuyện nhỏ của những đứa trẻ xảy ra ở một ngôi làng: chuyện về con cóc Cậu trời, chuyện ma, chuyện công chúa và hoàng tử, bên cạnh chuyện đói ăn, cháy nhà, lụt lội, “Tôi thấy hoa vàng trên cỏ xanh”hứa hẹn đem đến những điều thú vị với cả bạn đọc nhỏ tuổi và người lớn bằng giọng văn trong sáng, hồn nhiên, giản dị của trẻ con cùng nhiều tình tiết thú vị, bất ngờ và cảm động trong suốt hơn 300 trang sách. Cuốn sách, vì thế có sức ám ảnh, thu hút, hấp dẫn không thể bỏ qua.");
        b4.setPublisher(p1);
        b4.setCategory(c2);
        Collection<Author> auth4 = new ArrayList<>();
        auth4.add(a1);
        entityManager.merge(b4);
        Author au6 = entityManager.find(Author.class,"AUTH2");
        Book bk5 = entityManager.find(Book.class, "BOOK5");
        bk5.getAuthor().add(au6);
        au6.getBook().add(bk5);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}