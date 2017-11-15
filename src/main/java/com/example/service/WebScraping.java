package com.example.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WebScraping {

    private static final Logger log = LoggerFactory.getLogger(WebScraping.class);

    @Autowired
    private WebClient webClient;

    public void start() {
        try {
            final HtmlPage page = webClient.getPage("http://www.tesouro.fazenda.gov.br/tesouro-direto-precos-e-taxas-dos-titulos");
//            final HtmlTable table = (HtmlTable) page.getByXPath("//table[@class='tabelaPrecoseTaxas']").get(0);
//            for (final HtmlTableRow row : table.getRows()) {
//                for (final HtmlTableCell cell : row.getCells()) {
//                    System.out.println(cell.asText()+',');
//                }
//                System.out.println(" ");
//            }
            final List<HtmlTableRow> trs = page.getByXPath("//tr[@class='camposTesouroDireto']");
            trs.forEach(row -> {
                row.getCells().forEach(cell -> {
                    System.out.println(cell.asText());
                });
            });

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
