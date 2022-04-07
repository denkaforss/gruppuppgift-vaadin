package com.example.gruppuppgiftvaadin.frontend.components;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AlbumDetails extends VerticalLayout {

    Image albumImage = new Image("/images/avantasia.jpg", "src");
    H2 albumTitle = new H2("The Wicked Symphony");
    Paragraph albumDescription = new Paragraph("The Wicked Symphony är det fjärde power metalalbumet skapat av Avantasia, ett av Tobias Sammets projekt. På skivan medverkar flertalet musiker från diverse olika band, exempelvis Tim \"Ripper\" Owens som tidigare sjöng i Judas Priest och Iced Earth. Den släpptes den 3 april 2010, samtidigt som albumet Angel of Babylon släpptes. De gavs ut både som en samlingsbox med båda albumen tillsammans och som egna album. Det är den andra delen av \"The Scarecrow Saga\". ");
    H2 artistName = new H2("Avantasia");
    Paragraph artistDescription = new Paragraph("Avantasia är ett power metal-projekt skapat av Edguys sångare Tobias Sammet. Hittills har sju skivor givits ut (varav fem är album); The Metal Opera Part I, The Metal Opera Part II, Lost In Space part I (EP), Lost In Space Part II (EP), The Scarecrow, Angel of Babylon och The Wicked Symphony. Dessutom har man också gett ut två samlingsalbum: The Metal Opera Pt 1 & 2 (Gold Edition) och Lost in Space Pt 1 & 2. Den förstnämnda innehåller ett 44 sidor tjockt konvolut med bilder, intervjuer och dylikt. En genomgående handling finns genom de två första skivorna som kan följas både genom låtarna och de tillhörande konvoluten. Skivorna innehåller omväxlande lugna och hårda musikaliska partier, powermetal blandas med körsång och orkestrar. Därför kallas Avantasias musik ofta för symphonic power metal. Operans namn kommer av orden Avalon och Fantasia.\n" +
            "\n" +
            "Skillnaden mellan Edguy och Avantasia, utom medlemmar, är att Avatasias låtar handlar mer om kärlek. Samt att Avantasia är mer inriktade mot fantasi, likt Nightwish. ");

    public AlbumDetails() {
        this.setVisible(false);

        albumImage.setWidth("80%");

        add(
                albumImage,
                albumTitle,
                albumDescription,
                artistName,
                artistDescription
                );
    }

}
