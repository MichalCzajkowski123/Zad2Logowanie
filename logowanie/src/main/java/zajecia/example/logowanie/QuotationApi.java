package zajecia.example.logowanie;

import com.sun.org.apache.xpath.internal.operations.Quo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotationApi {
    private List<Quotation> quotationList;

    public QuotationApi()
    {
        this.quotationList = new ArrayList<>();
        quotationList.add(new Quotation("Lepiej zaliczać się do niektórych, niż do wszystkich.", "Andrzej Sapkowski"));
        quotationList.add(new Quotation("Czytanie książek to najpiękniejsza zabawa, jaką sobie ludzkość wymyśliła.", "Wisława Szymborska"));
        quotationList.add(new Quotation("Dobrze widzi się tylko sercem. Najważniejsze jest niewidoczne dla oczu.", "Antoine de Saint-Exupéry"));
        quotationList.add(new Quotation("– To wódka? – słabym głosem zapytała Małgorzata.(...)\n" +
                "– Na litość boską, królowo – zachrypiał – czy ośmieliłbym się nalać damie wódki? To czysty spirytus.", "Michaił Bułhakow"));
    }
    @GetMapping("/api/")
    public List<Quotation> getQuotationList()
    {
        return quotationList;
    }
    @PostMapping("/api/")
    public boolean addQuotation(@RequestBody Quotation quotation)
    {
        return quotationList.add(quotation);
    }
    @DeleteMapping("/api")
    public void deleteQuotation(@RequestParam int index)
    {
        quotationList.remove(index);
    }
}
