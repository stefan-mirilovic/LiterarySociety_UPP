import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BookDisplay } from 'src/app/model/book-display';
import { Genre } from 'src/app/model/genre';
import { GenreDisplay } from 'src/app/model/genre-display';
import { Transaction } from 'src/app/model/transaction';
import { BookService } from 'src/app/service/book.service';
import { GenreService } from 'src/app/service/genre.service';
import { TransactionService } from 'src/app/service/transaction.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
  books: BookDisplay[];
  genres: GenreDisplay[];
  synopsisMaxLength: number = 400;
  title: string;
  loading: boolean;
  noOfPages: number;
  pageNo: number;
  resultsPerPage: number;
  selectedGenre: GenreDisplay

  constructor(
    private transactionService: TransactionService,
    private bookService: BookService,
    private toastr: ToastrService,
    private genreService: GenreService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loading = true;
    this.title = "Chronological Order";
    this.books = [];
    this.resultsPerPage = 10;
    this.pageNo = 0;
    this.noOfPages = 1;
    this.getBooks();
    this.books.push(
      new BookDisplay(0, "SPQR: Istorija starog Rima", "Meri Bird", "„Prekretnica, osveženje, revolucija. Potpuno novi pristup istoriji starog veka.“ Spectator\nSveobuhvatna, majstorski napisana istorija Rimskog carstva iz pera vodećeg svetskog stručnjaka pokazuje zašto je Rim „važan ljudima mnogo vekova kasnije“ (Atlantic). U delu SPQR, koje je odmah po objavljivanju postalo klasik, Meri Bird iznosi istoriju Rima „sa strašću i bez monotonog stručnog žargona“ i pokazuje kako je od „pomalo neuglednog sela iz gvozdenog doba“ Rim postao „neosporni vladar Sredozemlja“ (Wall Street Journal).\nKritičari su pozdravili ovu knjigu koja dočarava „široku sliku i intimne pojedinosti koje oživljavaju daleku prošlost“ (Economist). Obuhvatajući gotovo hiljadu godina istorije, ovo „vrlo informativno i vrlo čitljivo delo“ (Dallas Morning News) ne samo što istražuje način na koji razmišljamo o drevnom Rimu, nego i osporava ukorenjene istorijske stavove koji su vladali stolećima. Suptilnim skretanjem pažnje na staleže, borbu za demokratiju i živote čitavih društvenih grupa vekovima izostavljanih iz istorijske pripovesti, SPQR će u sledećim decenijama oblikovati naš pogled na Rim.\n„Prefinjeno i zavodljivo preispitivanje složenih i međusobno protivrečnih pisanih i materijalnih tragova rimskog sveta.“ Guardian\n „Vrhunska naučnica i smela rušiteljka mitova, Meri Bird poučava i zabavlja nimalo ne potcenjujući svoje čitaoce. SPQR je ozbiljno delo brzog tempa, važno i ikonoklastično.“ Independent\n„Ovo je prema svim merilima veličanstvena povest koju bi se malo koji sadašnji naučnik usudio da ponudi.“ History Today\n„Uzbudljivo, psihološki oštroumno, blagonaklono podozrivo.“ Sunday Times", false, 59.99)
    );
    this.books.push(
      new BookDisplay(1, "Najzabavnije igre i izazovi", "Grupa autora", "Koji je tvoj najbolji rezultat?\nOva knjiga je puna neobičnih, otkačenih i blesavih izazova. Nekada ćeš morati da pobediš vreme, nekada prijatelje, ali često ćeš se takmičiti protiv sebe i pokušati da postaviš svoj lični rekord.\nIgre, zadaci i izazovi koje ćeš ovde naći pomoći će ti da stekneš nove veštine i konačno savladaš svog najgoreg protivnika – dosadu.", true, 39.99)
    );
    this.books.push(
      new BookDisplay(2, "Da je bolje, ne bi valjalo", "Bojan Ljubenović", "Početkom aprila sam hteo da se ubijem.\nSredinom aprila su hteli da me ubiju.\nKrajem aprila odlučio sam da ubijem.\nZanimljiv mesec, mora se priznati.\nKada neostvareni pisac Ivan Bogdan u svom bračnom krevetu zatekne voljenu suprugu sa drugim muškarcem, uveren je da je njegov život dotakao samo dno. Ali on zaboravlja da živi u zemlji koja često podseća na kofer sa duplim dnom, te da uvek postoji još jedna pregrada ispod. Njegov ljubavni problem uskoro prelazi u drugi plan jer ga sustiže mnogo gori. Jedan bizarni trenutak nepažnje upliće Ivana u neuspešnu pljačku banke i uspešno ga uvodi u surovi svet beogradskog podzemlja. Naoružan samo urnebesnim smislom za humor glavni junak započinje iscrpljujuću borbu za spas svih koje voli ali i sebe samog. Priča postaje sve dinamičnija i dobija neverovatne obrte, a Ivan otkriva da je i te kako sposoban za dela koje su mu ranija bila nepojmljiva.\n„Ovaj roman je teško smestiti u određeni žanr, ali se sa sigurnošću može reći da je reč o knjizi koja se čita u jednom dahu. To nije samo priča o ljubavi, niti je samo napeti triler, već je i slika savremenog srpskog društva koju Bojan Ljubenović uspeva da izoštri i ogoli svojim britkim humorom. Na momente smešan, na momente gorak, roman će sigurno osvojiti čitaoca. Da je bolji, ne bi valjalo.“ Darko Crnogorac, Njuz.net", false, 49.99)
    );
    this.books.push(
      new BookDisplay(3, "Cilkin put", "Heder Moris", "Od autorke bestselera Tetovažer iz Aušvica\nZasnovano na potresnoj istinitoj priči o ljubavi i opstanku\nLepota ju je spasla – i osudila.\nCilka je imala svega šesnaest godina kad je 1942. odvedena u koncentracioni logor Aušvic-Birkenau, gde je komandant odmah primetio njenu lepotu. Zbog toga će biti prisilno odvojena od ostalih zatvorenica, i ubrzo shvatiti da moć, čak i nerado prihvaćena, znači opstanak.\n Završetak rata i oslobađanje logora Cilki neće doneti slobodu već optužbu za kolaboraciju jer je spavala s neprijateljem i osudu na zatočeništvo u sibirskom logoru. Ali da li je zaista imala izbora? I gde su granice morala za Cilku, koja je odvedena u Aušvic kad je još bila dete?\nU Sibiru se Cilka suočava i sa novim i sa starim, jezivim izazovima uključujući i neželjenu pažnju stražara. Ali kad upozna ljubaznu lekarku, Cilka dospeva pod njeno okrilje i počinje da neguje bolesne logoraše trudeći se da im pomogne u surovim uslovima.\nSvakodnevno se suočavajući sa smrću i užasom, Cilka u sebi otkriva snagu kakvu nije ni znala da poseduje. A kad počne da s oklevanjem gradi veze i odnose u toj surovoj novoj stvarnosti, Cilka shvata da, uprkos svemu što joj se dogodilo, u njenom srcu ima mesta za ljubav.\nOd deteta do žene, od žene do isceliteljke, Cilkin put rasvetljava izdržljivost ljudskog duha i našu volju da preživimo.\n„Nikad nisam sreo hrabriju osobu od Cilke.“ Lali Sokolov, tetovažer iz Aušvica\n„Cilkina neizmerna hrabrost i odlučnost da preživi čak i kad je svaka nada već izgubljena bude veliko poštovanje. Potresna priča koju ćete dugo pamtiti.“ Sunday Express\n„Izuzetna priča o snazi da se prevaziđu nezamislive teškoće.“ Woman’s Weekly", false, 49.99)
    );
    this.books.push(
      new BookDisplay(3, "Cilkin put", "Heder Moris", "Od autorke bestselera Tetovažer iz Aušvica\nZasnovano na potresnoj istinitoj priči o ljubavi i opstanku\nLepota ju je spasla – i osudila.\nCilka je imala svega šesnaest godina kad je 1942. odvedena u koncentracioni logor Aušvic-Birkenau, gde je komandant odmah primetio njenu lepotu. Zbog toga će biti prisilno odvojena od ostalih zatvorenica, i ubrzo shvatiti da moć, čak i nerado prihvaćena, znači opstanak.\n Završetak rata i oslobađanje logora Cilki neće doneti slobodu već optužbu za kolaboraciju jer je spavala s neprijateljem i osudu na zatočeništvo u sibirskom logoru. Ali da li je zaista imala izbora? I gde su granice morala za Cilku, koja je odvedena u Aušvic kad je još bila dete?\nU Sibiru se Cilka suočava i sa novim i sa starim, jezivim izazovima uključujući i neželjenu pažnju stražara. Ali kad upozna ljubaznu lekarku, Cilka dospeva pod njeno okrilje i počinje da neguje bolesne logoraše trudeći se da im pomogne u surovim uslovima.\nSvakodnevno se suočavajući sa smrću i užasom, Cilka u sebi otkriva snagu kakvu nije ni znala da poseduje. A kad počne da s oklevanjem gradi veze i odnose u toj surovoj novoj stvarnosti, Cilka shvata da, uprkos svemu što joj se dogodilo, u njenom srcu ima mesta za ljubav.\nOd deteta do žene, od žene do isceliteljke, Cilkin put rasvetljava izdržljivost ljudskog duha i našu volju da preživimo.\n„Nikad nisam sreo hrabriju osobu od Cilke.“ Lali Sokolov, tetovažer iz Aušvica\n„Cilkina neizmerna hrabrost i odlučnost da preživi čak i kad je svaka nada već izgubljena bude veliko poštovanje. Potresna priča koju ćete dugo pamtiti.“ Sunday Express\n„Izuzetna priča o snazi da se prevaziđu nezamislive teškoće.“ Woman’s Weekly", false, 49.99)
    );
    this.genres = [];
    /*this.genres.push(new GenreDisplay(0, "Mystery", false))
    this.genres.push(new GenreDisplay(1, "Comedy", false))
    this.genres.push(new GenreDisplay(2, "Adventure", false))
    this.genres.push(new GenreDisplay(3, "Children's", false))*/
    this.genreService.getAllGenresSorted().subscribe({
      next: (results) => {
        for (let result of results) {
          this.genres.push(new GenreDisplay(result.id, result.name, false))
        }
      }
    })
  }

  getBooks() {
    this.books = [];
    this.bookService.findAllWithPagination(this.resultsPerPage, this.pageNo, this.selectedGenre ? this.selectedGenre.id : undefined).subscribe({
      next: (results) => {
        this.books = results;
        if (results.length != 0) {
          this.noOfPages = results[0].noOfPages;
        }
        this.loading = false;
        for (let book of this.books) {
          if (book.synopsis.length > this.synopsisMaxLength) {
            book.synopsis = book.synopsis.substr(0, this.synopsisMaxLength-3) + "..."
          }
          book.color = this.randomColorBackground();
        }
      },
      error: data => {
        this.loading = false;
        if (data.error && typeof data.error === "string")
          this.toastr.error(data.error);
        else
          this.toastr.error("An error occured while getting books!")
      }
    })
  }

  randomColor() {
    //return Math.floor(Math.random()*16777215).toString(16);
    let letters = '0123456789ABCDEF'.split('');
    let color = '#';
    for (let i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

  randomColorBackground() {
    let r = this.randomColor()
    console.log(r);
    return "background-color: " + r;
  }

  genreChanged(g: GenreDisplay) {
    for (let genre of this.genres) {
      if (genre.id !== g.id) {
        genre.checked = false;
      }
    }
    this.selectedGenre = g;
    if (g.checked)
      this.title = g.name;
    else {
      this.title = "Chronological Order";
      this.selectedGenre = undefined;
    }
    this.getBooks();
  }

  previousPage() {
    --this.pageNo;
    this.loading = true;
    this.getBooks();
  }

  nextPage() {
    ++this.pageNo;
    this.loading = true;
    this.getBooks();
  }

  onResultPerPageChange() {
    this.pageNo = 0;
    this.loading = true;
    this.getBooks();
  }

  details(book: BookDisplay) {
    localStorage.setItem("prevPage", "reader-dashboard/store")
    this.router.navigate([`/reader-dashboard/book/${book.id}`]);
  }

  purchase(event, book: BookDisplay) {
    event.stopPropagation();
    let transaction: Transaction = new Transaction(null, null, null, book.id, null);
    this.transactionService.create(transaction).subscribe({
      next: (result) => {
        window.location.href = `${environment.paymentUrl}?merchantId=${result.merchantId}&amount=${result.amount}&successUrl=${result.successUrl}&failedUrl=${result.failedUrl}&errorUrl=${result.errorUrl}`;
      },
      error: data => {
        this.loading = false;
        if (data.error && typeof data.error === "string")
          this.toastr.error(data.error);
        else
          this.toastr.error("An error occured while purchasing book!")
      }
    })
  }

  download(event, book: BookDisplay) {
    event.stopPropagation();
    this.toastr.info("Placeholder");
  }
}
