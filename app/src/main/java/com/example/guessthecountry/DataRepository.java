package com.example.guessthecountry;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataRepository{
    public static List<Country> easyEurope() {
        List<Country> europeCountries = new ArrayList<>();
        europeCountries.add(new Country("Австрия", "at", "atm", "Виена"));
        europeCountries.add(new Country("Белгия", "be", "bem", "Брюксел"));
        europeCountries.add(new Country("Дания", "dk", "dkm", "Копенхаген"));
        europeCountries.add(new Country("Франция", "fr", "frm", "Париж"));
        europeCountries.add(new Country("Германия", "de", "dem", "Берлин"));
        europeCountries.add(new Country("Гърция", "gr", "grm", "Атина"));
        europeCountries.add(new Country("Унгария", "hu", "hum", "Будапеща"));
        europeCountries.add(new Country("Италия", "it", "itm", "Рим"));
        europeCountries.add(new Country("Нидерландия", "nl", "nlm", "Амстердам"));
        europeCountries.add(new Country("Полша", "pl", "plm", "Варшава"));
        europeCountries.add(new Country("Португалия", "pt", "ptm", "Лисабон"));
        europeCountries.add(new Country("Русия", "ru", "rum", "Москва"));
        europeCountries.add(new Country("Сърбия", "rs", "rsm", "Белград"));
        europeCountries.add(new Country("Испания", "es", "esm", "Мадрид"));
        europeCountries.add(new Country("Обединеното кралство", "gb_nir", "gb_nirm", "Лондон"));
        return europeCountries;
    }

    public static List<Country> easyAsia() {
        List<Country> asiaCountries = new ArrayList<>();
        asiaCountries.add(new Country("Армения", "am", "amm", "Ереван"));
        asiaCountries.add(new Country("Азербайджан", "az", "azm", "Баку"));
        asiaCountries.add(new Country("Китай", "cn", "cnm", "Пекин"));
        asiaCountries.add(new Country("Кипър", "cy", "cym", "Никозия"));
        asiaCountries.add(new Country("Грузия", "ge", "gem", "Тбилиси"));
        asiaCountries.add(new Country("Индия", "in", "inm", "Ню Делхи"));
        asiaCountries.add(new Country("Индонезия", "id", "idm", "Джакарта"));
        asiaCountries.add(new Country("Иран", "ir", "irm", "Техеран"));
        asiaCountries.add(new Country("Ирак", "iq", "iqm", "Багдад"));
        asiaCountries.add(new Country("Израел", "il", "ilm", "Ерусалим"));
        asiaCountries.add(new Country("Япония", "jp", "jpm", "Токио"));
        asiaCountries.add(new Country("Филипини", "ph", "phm", "Манила"));
        asiaCountries.add(new Country("Южна Корея", "kr", "krm", "Сеул"));
        asiaCountries.add(new Country("Тайланд", "th", "thm", "Банкок"));
        asiaCountries.add(new Country("Обединените арабски емирства", "ae", "aem", "Абу Даби"));
        return asiaCountries;
    }

    public static List<Country> easyAfrica() {
        List<Country> africaCountries = new ArrayList<>();
        africaCountries.add(new Country("Алжир", "dz", "dzm", "Алжир"));
        africaCountries.add(new Country("Демократична република Конго", "cd", "cdm", "Киншаса"));
        africaCountries.add(new Country("Египет", "eg", "egm", "Кайро"));
        africaCountries.add(new Country("Етиопия", "et", "etm", "Адис Абеба"));
        africaCountries.add(new Country("Гвинея-Бисау", "gw", "gwm", "Бисау"));
        africaCountries.add(new Country("Кения", "ke", "kem", "Найроби"));
        africaCountries.add(new Country("Либерия", "lr", "lrm", "Монровия"));
        africaCountries.add(new Country("Либия", "ly", "lym", "Триполи"));
        africaCountries.add(new Country("Мароко", "ma", "mam", "Рабат"));
        africaCountries.add(new Country("Нигерия", "ng", "ngm", "Абуджа"));
        africaCountries.add(new Country("Сенегал", "sn", "snm", "Дакар"));
        africaCountries.add(new Country("Сомалия", "so", "som", "Могадишу"));
        africaCountries.add(new Country("Южна Африка", "za", "zam", "Претория"));
        africaCountries.add(new Country("Южен Судан", "ss", "ssm", "Джуба"));
        africaCountries.add(new Country("Танзания", "tz", "tzm", "Додома"));
        africaCountries.add(new Country("Тунис", "tn", "tnm", "Тунис"));
        africaCountries.add(new Country("Уганда", "ug", "ugm", "Кампала"));
        return africaCountries;
    }

    public static List<Country> easyAmerica() {
        List<Country> americaCountries = new ArrayList<>();
        americaCountries.add(new Country("Ел Салвадор", "sv", "svm", "Сан Салвадор"));
        americaCountries.add(new Country("Канада", "ca", "cam", "Отава"));
        americaCountries.add(new Country("Куба", "cu", "cum", "Хавана"));
        americaCountries.add(new Country("Мексико", "mx", "mxm", "Мексико Сити"));
        americaCountries.add(new Country("Съединените щати", "us", "usm", "Вашингтон, Д.К."));
        americaCountries.add(new Country("Аржентина", "ar", "arm", "Буенос Айрес"));
        americaCountries.add(new Country("Бразилия", "br", "brm", "Бразилия"));
        americaCountries.add(new Country("Чили", "cl", "clm", "Сантяго"));
        americaCountries.add(new Country("Колумбия", "co", "com", "Богота"));
        americaCountries.add(new Country("Еквадор", "ec", "ecm", "Кито"));
        americaCountries.add(new Country("Перу", "pe", "pem", "Лима"));
        americaCountries.add(new Country("Уругвай", "uy", "uym", "Монтевидео"));
        americaCountries.add(new Country("Венецуела", "ve", "vem", "Каракас"));
        americaCountries.add(new Country("Австралия", "au", "aum", "Канбера"));
        americaCountries.add(new Country("Нова Зеландия", "nz", "nzm", "Уелингтън"));
        americaCountries.add(new Country("Ямайка", "jm", "jmm", "Кингстън"));
        return americaCountries;
    }

    public static List<Country> mediumEurope() {
        List<Country> europeCountries = new ArrayList<>();
        europeCountries.add(new Country("Беларус", "by", "bym", "Минск"));
        europeCountries.add(new Country("България", "bg", "bgm", "София"));
        europeCountries.add(new Country("Хърватия", "hr", "hrm", "Загреб"));
        europeCountries.add(new Country("Чехия", "cz", "czm", "Прага"));
        europeCountries.add(new Country("Финландия", "fi", "fim", "Хелзинки"));
        europeCountries.add(new Country("Исландия", "is", "ism", "Рейкявик"));
        europeCountries.add(new Country("Ирландия", "ie", "iem", "Дъблин"));
        europeCountries.add(new Country("Северна Македония", "mk", "mkm", "Скопие"));
        europeCountries.add(new Country("Норвегия", "no", "nom", "Осло"));
        europeCountries.add(new Country("Румъния", "ro", "rom", "Букурещ"));
        europeCountries.add(new Country("Словакия", "sk", "skm", "Братислава"));
        europeCountries.add(new Country("Словения", "si", "sim", "Любляна"));
        europeCountries.add(new Country("Швеция", "se", "sem", "Стокхолм"));
        europeCountries.add(new Country("Швейцария", "ch", "chm", "Берн"));
        europeCountries.add(new Country("Турция", "tr", "trm", "Анкара"));
        europeCountries.add(new Country("Украйна", "ua", "uam", "Киев"));
        return europeCountries;
    }

    public static List<Country> mediumAsia() {
        List<Country> asiaCountries = new ArrayList<>();
        asiaCountries.add(new Country("Афганистан", "af", "afm", "Кабул"));
        asiaCountries.add(new Country("Бангладеш", "bd", "bdm", "Дака"));
        asiaCountries.add(new Country("Йордания", "jo", "jom", "Аман"));
        asiaCountries.add(new Country("Казахстан", "kz", "kzm", "Астана"));
        asiaCountries.add(new Country("Ливан", "lb", "lbm", "Бейрут"));
        asiaCountries.add(new Country("Малайзия", "my", "mym", "Куала Лумпур"));
        asiaCountries.add(new Country("Монголия", "mn", "mnm", "Улан Батор"));
        asiaCountries.add(new Country("Мианмар", "mm", "mmm", "Найпидо"));
        asiaCountries.add(new Country("Непал", "np", "npm", "Катманду"));
        asiaCountries.add(new Country("Пакистан", "pk", "pkm", "Исламабад"));
        asiaCountries.add(new Country("Саудитска Арабия", "sa", "sam", "Рияд"));
        asiaCountries.add(new Country("Сингапур", "sg", "sgm", "Сингапур"));
        asiaCountries.add(new Country("Сирия", "sy", "sym", "Дамаск"));
        asiaCountries.add(new Country("Таджикистан", "tj", "tjm", "Душанбе"));
        asiaCountries.add(new Country("Узбекистан", "uz", "uzm", "Ташкент"));
        asiaCountries.add(new Country("Виетнам", "vn", "vnm", "Ханой"));
        return asiaCountries;
    }

    public static List<Country> mediumAfrica() {
        List<Country> africaCountries = new ArrayList<>();
        africaCountries.add(new Country("Ангола", "ao", "aom", "Луанда"));
        africaCountries.add(new Country("Буркина Фасо", "bf", "bfm", "Уагадугу"));
        africaCountries.add(new Country("Кабо Верде", "cv", "cvm", "Прайя"));
        africaCountries.add(new Country("Камерун", "cm", "cmm", "Яунде"));
        africaCountries.add(new Country("Централна Африканска Република", "cf", "cfm", "Банги"));
        africaCountries.add(new Country("Република Конго", "cg", "cgm", "Бразавил"));
        africaCountries.add(new Country("Чад", "td", "tdm", "Нджамена"));
        africaCountries.add(new Country("Еритрея", "er", "erm", "Асмара"));
        africaCountries.add(new Country("Габон", "ga", "gam", "Либревил"));
        africaCountries.add(new Country("Гамбия", "gm", "gmm", "Банджул"));
        africaCountries.add(new Country("Гана", "gh", "ghm", "Акра"));
        africaCountries.add(new Country("Гвинея", "gn", "gnm", "Конакри"));
        africaCountries.add(new Country("Кот д'Ивоар", "ci", "cim", "Ямусукро"));
        africaCountries.add(new Country("Екваториална Гвинея", "gq", "gqm", "Малабо"));
        africaCountries.add(new Country("Сиера Леоне", "sl", "slm", "Фрийтаун"));
        africaCountries.add(new Country("Судан", "sd", "sdm", "Хартум"));
        africaCountries.add(new Country("Мавритания", "mr", "mrm", "Нуакшот"));
        africaCountries.add(new Country("Мозамбик", "mz", "mzm", "Мапуту"));
        return africaCountries;
    }

    public static List<Country> mediumAmerica() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Доминиканска република", "dom", "domm", "Санто Доминго"));
        countries.add(new Country("Бахамски острови", "bs", "bsm", "Насау"));
        countries.add(new Country("Барбадос", "bb", "bbm", "Бриджтаун"));
        countries.add(new Country("Папуа Нова Гвинея", "pg", "pgm", "Порт Морсби"));
        countries.add(new Country("Самоа", "ws", "wsm", "Апия"));
        countries.add(new Country("Соломонови острови", "sb", "sbm", "Хониара"));
        countries.add(new Country("Панама", "pa", "pam", "Панама Сити"));
        countries.add(new Country("Гватемала", "gt", "gtm", "Гватемала Сити"));
        countries.add(new Country("Хаити", "ht", "htm", "Порт-о-Пренс"));
        countries.add(new Country("Хондурас", "hn", "hnm", "Тегусигалпа"));
        countries.add(new Country("Тринидад и Тобаго", "tt", "ttm", "Порт оф Спейн"));
        countries.add(new Country("Боливия", "bo", "bom", "Сукре"));
        countries.add(new Country("Гаяна", "gy", "gym", "Джорджтаун"));
        countries.add(new Country("Парагвай", "py", "pym", "Асунсион"));
        countries.add(new Country("Суринам", "sr", "srm", "Парамарибо"));
        countries.add(new Country("Фиджи", "fj", "fjm", "Сува"));
        return countries;
    }

    public static List<Country> hardEurope() {
        List<Country> europeCountries = new ArrayList<>();
        europeCountries.add(new Country("Албания", "al", "alm", "Тирана"));
        europeCountries.add(new Country("Андора", "ad", "adm", "Андора ла Веля"));
        europeCountries.add(new Country("Босна и Херцеговина", "ba", "bam", "Сараево"));
        europeCountries.add(new Country("Естония", "ee", "eem", "Талин"));
        europeCountries.add(new Country("Косово", "xk", "xkm", "Прищина"));
        europeCountries.add(new Country("Латвия", "lv", "lvm", "Рига"));
        europeCountries.add(new Country("Лихтенщайн", "li", "lim", "Вадуц"));
        europeCountries.add(new Country("Литва", "lt", "ltm", "Вилнюс"));
        europeCountries.add(new Country("Люксембург", "lu", "lum", "Люксембург"));
        europeCountries.add(new Country("Малта", "mt", "mtm", "Валета"));
        europeCountries.add(new Country("Молдова", "md", "mdm", "Кишинев"));
        europeCountries.add(new Country("Монако", "mc", "mcm", "Монако"));
        europeCountries.add(new Country("Черна гора", "me", "mem", "Подгорица"));
        europeCountries.add(new Country("Сан Марино", "sm", "smm", "Сан Марино"));
        europeCountries.add(new Country("Ватиканска държава", "va", "vam", "Ватикан"));
        return europeCountries;
    }

    public static List<Country> hardAsia() {
        List<Country> asiaCountries = new ArrayList<>();
        asiaCountries.add(new Country("Бахрейн", "bh", "bhm", "Манама"));
        asiaCountries.add(new Country("Бутан", "bt", "btm", "Тхимпху"));
        asiaCountries.add(new Country("Бруней", "bn", "bnm", "Бандар Сери Бегаван"));
        asiaCountries.add(new Country("Камбоджа", "kh", "khm", "Пном Пен"));
        asiaCountries.add(new Country("Кувейт", "kw", "kwm", "Кувейт Сити"));
        asiaCountries.add(new Country("Киргизстан", "kg", "kgm", "Бишкек"));
        asiaCountries.add(new Country("Лаос", "la", "lam", "Виентян"));
        asiaCountries.add(new Country("Малдиви", "mv", "mvm", "Мале"));
        asiaCountries.add(new Country("Северна Корея", "kp", "kpm", "Пхеньонг"));
        asiaCountries.add(new Country("Оман", "om", "omm", "Мускат"));
        asiaCountries.add(new Country("Палестина", "ps", "psm", "Рамала"));
        asiaCountries.add(new Country("Катар", "qa", "qam", "Доха"));
        asiaCountries.add(new Country("Шри Ланка", "lk", "lkm", "Шри Джаяварденепура Коте"));
        asiaCountries.add(new Country("Туркменистан", "tm", "tmm", "Ашхабад"));
        asiaCountries.add(new Country("Източен Тимор", "tl", "tlm", "Дили"));
        asiaCountries.add(new Country("Йемен", "ye", "yem", "Сана"));
        return asiaCountries;
    }

    public static List<Country> hardAfrica() {
        List<Country> africaCountries = new ArrayList<>();
        africaCountries.add(new Country("Бенин", "bj", "bjm", "Порто-Ново"));
        africaCountries.add(new Country("Ботсвана", "bw", "bwm", "Габороне"));
        africaCountries.add(new Country("Бурунди", "bi", "bim", "Гитега"));
        africaCountries.add(new Country("Коморски острови", "km", "kmm", "Морони"));
        africaCountries.add(new Country("Джибути", "dj", "djm", "Джибути"));
        africaCountries.add(new Country("Есватини", "esw", "eswm", "Мбабане"));
        africaCountries.add(new Country("Лесото", "ls", "lsm", "Масеру"));
        africaCountries.add(new Country("Мадагаскар", "mg", "mgm", "Антананариво"));
        africaCountries.add(new Country("Малави", "mw", "mwm", "Лилонгве"));
        africaCountries.add(new Country("Мали", "ml", "mlm", "Бамако"));
        africaCountries.add(new Country("Мавриций", "mu", "mum", "Порт Луи"));
        africaCountries.add(new Country("Намибия", "na", "nam", "Виндхук"));
        africaCountries.add(new Country("Нигер", "ne", "nem", "Ниамей"));
        africaCountries.add(new Country("Руанда", "rw", "rwm", "Кигали"));
        africaCountries.add(new Country("Сао Томе и Принсипи", "st", "stm", "Сао Томе"));
        africaCountries.add(new Country("Сейшели", "sc", "scm", "Виктория"));
        africaCountries.add(new Country("Того", "tg", "tgm", "Ломе"));
        africaCountries.add(new Country("Замбия", "zm", "zm", "Лусака"));
        africaCountries.add(new Country("Зимбабве", "zw", "zwm", "Хараре"));
        return africaCountries;
    }

    public static List<Country> hardAmerica() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Антигуа и Барбуда", "ag", "agm", "Сейнт Джонс"));
        countries.add(new Country("Белиз", "bz", "bzm", "Белмопан"));
        countries.add(new Country("Коста Рика", "cr", "crm", "Сан Хосе"));
        countries.add(new Country("Доминика", "dm", "dmm", "Розо"));
        countries.add(new Country("Гренада", "gd", "gdm", "Сейнт Джорджс"));
        countries.add(new Country("Никарагуа", "ni", "nim", "Манагуа"));
        countries.add(new Country("Сейнт Китс и Невис", "kn", "knm", "Басетер"));
        countries.add(new Country("Сейнт Лусия", "lc", "lcm", "Кастри"));
        countries.add(new Country("Сейнт Винсент и Гренадини", "vc", "vcm", "Кингстаун"));
        countries.add(new Country("Кирибати", "ki", "kim", "Южен Тарава"));
        countries.add(new Country("Маршалови острови", "mh", "mhm", "Маджуро"));
        countries.add(new Country("Микронезия", "fm", "fmm", "Паликир"));
        countries.add(new Country("Науру", "nr", "nrm", "Ярен"));
        countries.add(new Country("Палау", "pw", "pwm", "Нгерулмуд"));
        countries.add(new Country("Тонга", "to", "tom", "Нукуалофа"));
        countries.add(new Country("Вануату", "vu", "vum", "Порт Вила"));
        return countries;
    }
    public static void writeUsersToFile(final Context context, final List<User> userList) {
        try {
            File path = context.getExternalFilesDir(null); // Get app-specific directory
            File file = new File(path, "winners.txt");
            FileOutputStream writer = new FileOutputStream(file);
            for (User user : userList) {
                String userString = user.getUsername() + "," + user.getPoints() + "\n";
                writer.write(userString.getBytes());
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing users to file: " + e.getMessage());
        }
    }
    public static List<User> readUsersFromFile(Context context) {
        List<User> userList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
        }
        try {
            File file = new File(context.getExternalFilesDir(null), "winners.txt");
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 2) {
                            String username = parts[0];
                            int points = Integer.parseInt(parts[1]);
                            userList.add(new User(username, points));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
        }
        return userList;
    }
}
