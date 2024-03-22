package com.example.guessthecountry.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.guessthecountry.DTO.Country;
import com.example.guessthecountry.DTO.User;
import com.example.guessthecountry.DTO.Winner;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "USERMANAGER";
    // Table Names
    private static final String USERS = "USERS";
    private static final String WINNERS = "WINNERS";
    // Common column names
    private static final String KEY_ID = "id";
    private static final String COUNTRIES = "COUNTRIES";
    private static final String KEY_CONTINENT = "continent";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_FLAG = "flag";
    private static final String KEY_MAP = "map";
    private static final String KEY_CAPITAL = "capital";

    private static final String KEY_NAME = "username";
    private static final String KEY_PH_NO = "password";
    private static final String KEY_POINTS = "points";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + USERS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_WINNERS_TABLE = "CREATE TABLE " + WINNERS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_POINTS + " INTEGER" + ")";
        db.execSQL(CREATE_WINNERS_TABLE);
        String CREATE_COUNTRIES_TABLE = "CREATE TABLE " + COUNTRIES +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_FLAG + " TEXT," + KEY_MAP + " TEXT," + KEY_CAPITAL + " TEXT,"
                + KEY_CONTINENT + " TEXT," + KEY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_COUNTRIES_TABLE);
        addCountry(db, "Австрия", "at", "atm", "Виена", "Europe", "Easy");
        addCountry(db, "Белгия", "be", "bem", "Брюксел", "Europe", "Easy");
        addCountry(db, "Дания", "dk", "dkm", "Копенхаген", "Europe", "Easy");
        addCountry(db, "Франция", "fr", "frm", "Париж", "Europe", "Easy");
        addCountry(db, "Германия", "de", "dem", "Берлин", "Europe", "Easy");
        addCountry(db, "Гърция", "gr", "grm", "Атина", "Europe", "Easy");
        addCountry(db, "Унгария", "hu", "hum", "Будапеща", "Europe", "Easy");
        addCountry(db, "Италия", "it", "itm", "Рим", "Europe", "Easy");
        addCountry(db, "Нидерландия", "nl", "nlm", "Амстердам", "Europe", "Easy");
        addCountry(db, "Полша", "pl", "plm", "Варшава", "Europe", "Easy");
        addCountry(db, "Португалия", "pt", "ptm", "Лисабон", "Europe", "Easy");
        addCountry(db, "Русия", "ru", "rum", "Москва", "Europe", "Easy");
        addCountry(db, "Сърбия", "rs", "rsm", "Белград", "Europe", "Easy");
        addCountry(db, "Испания", "es", "esm", "Мадрид", "Europe", "Easy");
        addCountry(db, "Обединеното кралство", "gb_nir", "gb_nirm", "Лондон", "Europe", "Easy");
        addCountry(db, "Армения", "am", "amm", "Ереван", "Asia", "Easy");
        addCountry(db, "Азербайджан", "az", "azm", "Баку", "Asia", "Easy");
        addCountry(db, "Китай", "cn", "cnm", "Пекин", "Asia", "Easy");
        addCountry(db, "Кипър", "cy", "cym", "Никозия", "Asia", "Easy");
        addCountry(db, "Грузия", "ge", "gem", "Тбилиси", "Asia", "Easy");
        addCountry(db, "Индия", "in", "inm", "Ню Делхи", "Asia", "Easy");
        addCountry(db, "Индонезия", "id", "idm", "Джакарта", "Asia", "Easy");
        addCountry(db, "Иран", "ir", "irm", "Техеран", "Asia", "Easy");
        addCountry(db, "Ирак", "iq", "iqm", "Багдад", "Asia", "Easy");
        addCountry(db, "Израел", "il", "ilm", "Йерусалим", "Asia", "Easy");
        addCountry(db, "Япония", "jp", "jpm", "Токио", "Asia", "Easy");
        addCountry(db, "Филипини", "ph", "phm", "Манила", "Asia", "Easy");
        addCountry(db, "Южна Корея", "kr", "krm", "Сеул", "Asia", "Easy");
        addCountry(db, "Тайланд", "th", "thm", "Банкок", "Asia", "Easy");
        addCountry(db, "Обединените арабски емирства", "ae", "aem", "Абу Даби", "Asia", "Easy");
        addCountry(db, "Алжир", "dz", "dzm", "Алжир", "Africa", "Easy");
        addCountry(db, "Демократична република Конго", "cd", "cdm", "Киншаса", "Africa", "Easy");
        addCountry(db, "Египет", "eg", "egm", "Кайро", "Africa", "Easy");
        addCountry(db, "Етиопия", "et", "etm", "Адис Абеба", "Africa", "Easy");
        addCountry(db, "Гвинея-Бисау", "gw", "gwm", "Бисау", "Africa", "Easy");
        addCountry(db, "Кения", "ke", "kem", "Найроби", "Africa", "Easy");
        addCountry(db, "Либерия", "lr", "lrm", "Монровия", "Africa", "Easy");
        addCountry(db, "Либия", "ly", "lym", "Триполи", "Africa", "Easy");
        addCountry(db, "Мароко", "ma", "mam", "Рабат", "Africa", "Easy");
        addCountry(db, "Нигерия", "ng", "ngm", "Абуджа", "Africa", "Easy");
        addCountry(db, "Сенегал", "sn", "snm", "Дакар", "Africa", "Easy");
        addCountry(db, "Сомалия", "so", "som", "Могадишу", "Africa", "Easy");
        addCountry(db, "Южна Африка", "za", "zam", "Претория", "Africa", "Easy");
        addCountry(db, "Южен Судан", "ss", "ssm", "Джуба", "Africa", "Easy");
        addCountry(db, "Танзания", "tz", "tzm", "Додома", "Africa", "Easy");
        addCountry(db, "Тунис", "tn", "tnm", "Тунис", "Africa", "Easy");
        addCountry(db, "Уганда", "ug", "ugm", "Кампала", "Africa", "Easy");
        addCountry(db, "Ел Салвадор", "sv", "svm", "Сан Салвадор", "America", "Easy");
        addCountry(db, "Канада", "ca", "cam", "Отава", "America", "Easy");
        addCountry(db, "Куба", "cu", "cum", "Хавана", "America", "Easy");
        addCountry(db, "Мексико", "mx", "mxm", "Мексико Сити", "America", "Easy");
        addCountry(db, "Съединените щати", "us", "usm", "Вашингтон, Д.К.", "America", "Easy");
        addCountry(db, "Аржентина", "ar", "arm", "Буенос Айрес", "America", "Easy");
        addCountry(db, "Бразилия", "br", "brm", "Бразилия", "America", "Easy");
        addCountry(db, "Чили", "cl", "clm", "Сантяго", "America", "Easy");
        addCountry(db, "Колумбия", "co", "com", "Богота", "America", "Easy");
        addCountry(db, "Еквадор", "ec", "ecm", "Кито", "America", "Easy");
        addCountry(db, "Перу", "pe", "pem", "Лима", "America", "Easy");
        addCountry(db, "Уругвай", "uy", "uym", "Монтевидео", "America", "Easy");
        addCountry(db, "Венецуела", "ve", "vem", "Каракас", "America", "Easy");
        addCountry(db, "Австралия", "au", "aum", "Канбера", "America", "Easy");
        addCountry(db, "Нова Зеландия", "nz", "nzm", "Уелингтън", "America", "Easy");
        addCountry(db, "Ямайка", "jm", "jmm", "Кингстън", "America", "Easy");
        addCountry(db, "Беларус", "by", "bym", "Минск", "Europe", "Medium");
        addCountry(db, "България", "bg", "bgm", "София", "Europe", "Medium");
        addCountry(db, "Хърватия", "hr", "hrm", "Загреб", "Europe", "Medium");
        addCountry(db, "Чехия", "cz", "czm", "Прага", "Europe", "Medium");
        addCountry(db, "Финландия", "fi", "fim", "Хелзинки", "Europe", "Medium");
        addCountry(db, "Исландия", "is", "ism", "Рейкявик", "Europe", "Medium");
        addCountry(db, "Ирландия", "ie", "iem", "Дъблин", "Europe", "Medium");
        addCountry(db, "Северна Македония", "mk", "mkm", "Скопие", "Europe", "Medium");
        addCountry(db, "Норвегия", "no", "nom", "Осло", "Europe", "Medium");
        addCountry(db, "Румъния", "ro", "rom", "Букурещ", "Europe", "Medium");
        addCountry(db, "Словакия", "sk", "skm", "Братислава", "Europe", "Medium");
        addCountry(db, "Словения", "si", "sim", "Любляна", "Europe", "Medium");
        addCountry(db, "Швеция", "se", "sem", "Стокхолм", "Europe", "Medium");
        addCountry(db, "Швейцария", "ch", "chm", "Берн", "Europe", "Medium");
        addCountry(db, "Турция", "tr", "trm", "Анкара", "Europe", "Medium");
        addCountry(db, "Украйна", "ua", "uam", "Киев", "Europe", "Medium");
        addCountry(db, "Афганистан", "af", "afm", "Кабул", "Asia", "Medium");
        addCountry(db, "Бангладеш", "bd", "bdm", "Дака", "Asia", "Medium");
        addCountry(db, "Йордания", "jo", "jom", "Аман", "Asia", "Medium");
        addCountry(db, "Казахстан", "kz", "kzm", "Астана", "Asia", "Medium");
        addCountry(db, "Ливан", "lb", "lbm", "Бейрут", "Asia", "Medium");
        addCountry(db, "Малайзия", "my", "mym", "Куала Лумпур", "Asia", "Medium");
        addCountry(db, "Монголия", "mn", "mnm", "Улан Батор", "Asia", "Medium");
        addCountry(db, "Мианмар", "mm", "mmm", "Найпидо", "Asia", "Medium");
        addCountry(db, "Непал", "np", "npm", "Катманду", "Asia", "Medium");
        addCountry(db, "Пакистан", "pk", "pkm", "Исламабад", "Asia", "Medium");
        addCountry(db, "Саудитска Арабия", "sa", "sam", "Рияд", "Asia", "Medium");
        addCountry(db, "Сингапур", "sg", "sgm", "Сингапур", "Asia", "Medium");
        addCountry(db, "Сирия", "sy", "sym", "Дамаск", "Asia", "Medium");
        addCountry(db, "Таджикистан", "tj", "tjm", "Душанбе", "Asia", "Medium");
        addCountry(db, "Узбекистан", "uz", "uzm", "Ташкент", "Asia", "Medium");
        addCountry(db, "Виетнам", "vn", "vnm", "Ханой", "Asia", "Medium");
        addCountry(db, "Ангола", "ao", "aom", "Луанда", "Africa", "Medium");
        addCountry(db, "Буркина Фасо", "bf", "bfm", "Уагадугу", "Africa", "Medium");
        addCountry(db, "Кабо Верде", "cv", "cvm", "Прайя", "Africa", "Medium");
        addCountry(db, "Камерун", "cm", "cmm", "Яунде", "Africa", "Medium");
        addCountry(db, "Централна Африканска Република", "cf", "cfm", "Банги", "Africa", "Medium");
        addCountry(db, "Република Конго", "cg", "cgm", "Бразавил", "Africa", "Medium");
        addCountry(db, "Чад", "td", "tdm", "Нджамена", "Africa", "Medium");
        addCountry(db, "Еритрея", "er", "erm", "Асмара", "Africa", "Medium");
        addCountry(db, "Габон", "ga", "gam", "Либревил", "Africa", "Medium");
        addCountry(db, "Гамбия", "gm", "gmm", "Банджул", "Africa", "Medium");
        addCountry(db, "Гана", "gh", "ghm", "Акра", "Africa", "Medium");
        addCountry(db, "Гвинея", "gn", "gnm", "Конакри", "Africa", "Medium");
        addCountry(db, "Кот д'Ивоар", "ci", "cim", "Ямусукро", "Africa", "Medium");
        addCountry(db, "Екваториална Гвинея", "gq", "gqm", "Малабо", "Africa", "Medium");
        addCountry(db, "Сиера Леоне", "sl", "slm", "Фрийтаун", "Africa", "Medium");
        addCountry(db, "Судан", "sd", "sdm", "Хартум", "Africa", "Medium");
        addCountry(db, "Мавритания", "mr", "mrm", "Нуакшот", "Africa", "Medium");
        addCountry(db, "Мозамбик", "mz", "mzm", "Мапуту", "Africa", "Medium");
        addCountry(db, "Доминиканска република", "dom", "domm", "Санто Доминго", "America", "Medium");
        addCountry(db, "Бахамски острови", "bs", "bsm", "Насау", "America", "Medium");
        addCountry(db, "Барбадос", "bb", "bbm", "Бриджтаун", "America", "Medium");
        addCountry(db, "Папуа Нова Гвинея", "pg", "pgm", "Порт Морсби", "America", "Medium");
        addCountry(db, "Самоа", "ws", "wsm", "Апия", "America", "Medium");
        addCountry(db, "Соломонови острови", "sb", "sbm", "Хониара", "America", "Medium");
        addCountry(db, "Панама", "pa", "pam", "Панама Сити", "America", "Medium");
        addCountry(db, "Гватемала", "gt", "gtm", "Гватемала Сити", "America", "Medium");
        addCountry(db, "Хаити", "ht", "htm", "Порт-о-Пренс", "America", "Medium");
        addCountry(db, "Хондурас", "hn", "hnm", "Тегусигалпа", "America", "Medium");
        addCountry(db, "Тринидад и Тобаго", "tt", "ttm", "Порт оф Спейн", "America", "Medium");
        addCountry(db, "Боливия", "bo", "bom", "Сукре", "America", "Medium");
        addCountry(db, "Гаяна", "gy", "gym", "Джорджтаун", "America", "Medium");
        addCountry(db, "Парагвай", "py", "pym", "Асунсион", "America", "Medium");
        addCountry(db, "Суринам", "sr", "srm", "Парамарибо", "America", "Medium");
        addCountry(db, "Фиджи", "fj", "fjm", "Сува", "America", "Medium");
        addCountry(db, "Албания", "al", "alm", "Тирана", "Europe", "Hard");
        addCountry(db, "Андора", "ad", "adm", "Андора ла Веля", "Europe", "Hard");
        addCountry(db, "Босна и Херцеговина", "ba", "bam", "Сараево", "Europe", "Hard");
        addCountry(db, "Естония", "ee", "eem", "Талин", "Europe", "Hard");
        addCountry(db, "Косово", "xk", "xkm", "Прищина", "Europe", "Hard");
        addCountry(db, "Латвия", "lv", "lvm", "Рига", "Europe", "Hard");
        addCountry(db, "Лихтенщайн", "li", "lim", "Вадуц", "Europe", "Hard");
        addCountry(db, "Литва", "lt", "ltm", "Вилнюс", "Europe", "Hard");
        addCountry(db, "Люксембург", "lu", "lum", "Люксембург", "Europe", "Hard");
        addCountry(db, "Малта", "mt", "mtm", "Валета", "Europe", "Hard");
        addCountry(db, "Молдова", "md", "mdm", "Кишинев", "Europe", "Hard");
        addCountry(db, "Монако", "mc", "mcm", "Монако", "Europe", "Hard");
        addCountry(db, "Черна гора", "me", "mem", "Подгорица", "Europe", "Hard");
        addCountry(db, "Сан Марино", "sm", "smm", "Сан Марино", "Europe", "Hard");
        addCountry(db, "Ватиканска държава", "va", "vam", "Ватикана", "Europe", "Hard");
        addCountry(db, "Бахрейн", "bh", "bhm", "Манама", "Asia", "Hard");
        addCountry(db, "Бутан", "bt", "btm", "Тхимпху", "Asia", "Hard");
        addCountry(db, "Бруней", "bn", "bnm", "Бандар Сери Бегаван", "Asia", "Hard");
        addCountry(db, "Камбоджа", "kh", "khm", "Пном Пен", "Asia", "Hard");
        addCountry(db, "Кувейт", "kw", "kwm", "Кувейт Сити", "Asia", "Hard");
        addCountry(db, "Киргизстан", "kg", "kgm", "Бишкек", "Asia", "Hard");
        addCountry(db, "Лаос", "la", "lam", "Виентян", "Asia", "Hard");
        addCountry(db, "Малдиви", "mv", "mvm", "Мале", "Asia", "Hard");
        addCountry(db, "Северна Корея", "kp", "kpm", "Пхеньонг", "Asia", "Hard");
        addCountry(db, "Оман", "om", "omm", "Мускат", "Asia", "Hard");
        addCountry(db, "Палестина", "ps", "psm", "Рамала", "Asia", "Hard");
        addCountry(db, "Катар", "qa", "qam", "Доха", "Asia", "Hard");
        addCountry(db, "Шри Ланка", "lk", "lkm", "Шри Джаяварденепура Коте", "Asia", "Hard");
        addCountry(db, "Туркменистан", "tm", "tmm", "Ашхабад", "Asia", "Hard");
        addCountry(db, "Източен Тимор", "tl", "tlm", "Дили", "Asia", "Hard");
        addCountry(db, "Йемен", "ye", "yem", "Сана", "Asia", "Hard");
        addCountry(db, "Бенин", "bj", "bjm", "Порто-Ново", "Africa", "Hard");
        addCountry(db, "Ботсвана", "bw", "bwm", "Габороне", "Africa", "Hard");
        addCountry(db, "Бурунди", "bi", "bim", "Гитега", "Africa", "Hard");
        addCountry(db, "Коморски острови", "km", "kmm", "Морони", "Africa", "Hard");
        addCountry(db, "Джибути", "dj", "djm", "Джибути", "Africa", "Hard");
        addCountry(db, "Есватини", "esw", "eswm", "Мбабане", "Africa", "Hard");
        addCountry(db, "Лесото", "ls", "lsm", "Масеру", "Africa", "Hard");
        addCountry(db, "Мадагаскар", "mg", "mgm", "Антананариво", "Africa", "Hard");
        addCountry(db, "Малави", "mw", "mwm", "Лилонгве", "Africa", "Hard");
        addCountry(db, "Мали", "ml", "mlm", "Бамако", "Africa", "Hard");
        addCountry(db, "Мавриций", "mu", "mum", "Порт Луи", "Africa", "Hard");
        addCountry(db, "Намибия", "na", "nam", "Виндхук", "Africa", "Hard");
        addCountry(db, "Нигер", "ne", "nem", "Ниамей", "Africa", "Hard");
        addCountry(db, "Руанда", "rw", "rwm", "Кигали", "Africa", "Hard");
        addCountry(db, "Сао Томе и Принсипи", "st", "stm", "Сао Томе", "Africa", "Hard");
        addCountry(db, "Сейшели", "sc", "scm", "Виктория", "Africa", "Hard");
        addCountry(db, "Того", "tg", "tgm", "Ломе", "Africa", "Hard");
        addCountry(db, "Замбия", "zm", "zm", "Лусака", "Africa", "Hard");
        addCountry(db, "Зимбабве", "zw", "zwm", "Хараре", "Africa", "Hard");
        addCountry(db, "Антигуа и Барбуда", "ag", "agm", "Сейнт Джонс", "America", "Hard");
        addCountry(db, "Белиз", "bz", "bzm", "Белмопан", "America", "Hard");
        addCountry(db, "Коста Рика", "cr", "crm", "Сан Хосе", "America", "Hard");
        addCountry(db, "Доминика", "dm", "dmm", "Розо", "America", "Hard");
        addCountry(db, "Гренада", "gd", "gdm", "Сейнт Джорджс", "America", "Hard");
        addCountry(db, "Никарагуа", "ni", "nim", "Манагуа", "America", "Hard");
        addCountry(db, "Сейнт Китс и Невис", "kn", "knm", "Басетер", "America", "Hard");
        addCountry(db, "Сейнт Лусия", "lc", "lcm", "Кастри", "America", "Hard");
        addCountry(db, "Сейнт Винсент и Гренадини", "vc", "vcm", "Кингстаун", "America", "Hard");
        addCountry(db, "Кирибати", "ki", "kim", "Южен Тарава", "America", "Hard");
        addCountry(db, "Маршалови острови", "mh", "mhm", "Маджуро", "America", "Hard");
        addCountry(db, "Микронезия", "fm", "fmm", "Паликир", "America", "Hard");
        addCountry(db, "Науру", "nr", "nrm", "Ярен", "America", "Hard");
        addCountry(db, "Палау", "pw", "pwm", "Нгерулмуд", "America", "Hard");
        addCountry(db, "Тонга", "to", "tom", "Нукуалофа", "America", "Hard");
        addCountry(db, "Вануату", "vu", "vum", "Порт Вила", "America", "Hard");
    }

    private void addCountry(SQLiteDatabase db, String name, String flag, String map, String capital, String continent, String level) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_FLAG, flag);
        values.put(KEY_MAP, map);
        values.put(KEY_CAPITAL, capital);
        values.put(KEY_CONTINENT, continent);
        values.put(KEY_LEVEL, level);
        db.insert(COUNTRIES, null, values);
    }

    public List<Country> getCountriesByFlag(String flag) {
        List<Country> countryList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(COUNTRIES, null, KEY_FLAG + "=?",
                new String[]{flag}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                country.setFlag(cursor.getString(cursor.getColumnIndexOrThrow(KEY_FLAG)));
                country.setMap(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MAP)));
                country.setCapital(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CAPITAL)));
                country.setContinent(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CONTINENT)));
                country.setLevel(cursor.getString(cursor.getColumnIndexOrThrow(KEY_LEVEL)));
                countryList.add(country);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return countryList;
    }

    public List<Country> getCountriesByDifficultyAndContinent(String level, String continent) {
        List<Country> countryList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(COUNTRIES, null, KEY_LEVEL + "=? AND " + KEY_CONTINENT + "=?",
                new String[]{level, continent}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                country.setFlag(cursor.getString(cursor.getColumnIndexOrThrow(KEY_FLAG)));
                country.setMap(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MAP)));
                country.setCapital(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CAPITAL)));
                country.setContinent(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CONTINENT)));
                country.setLevel(cursor.getString(cursor.getColumnIndexOrThrow(KEY_LEVEL)));
                countryList.add(country);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return countryList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        db.execSQL("DROP TABLE IF EXISTS " + WINNERS);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTRIES);
        onCreate(db);
    }

    public void onDeleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        db.execSQL("DROP TABLE IF EXISTS " + WINNERS);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTRIES);
        db.close();
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername());
        values.put(KEY_PH_NO, user.getPassword());
        db.insert(USERS, null, values);
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS + " WHERE " + KEY_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void addWinner(Winner winner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, winner.getUsername());
        values.put(KEY_POINTS, winner.getPoints());
        db.insert(WINNERS, null, values);
        db.close();
    }

    public List<Winner> getAllWinners() {
        List<Winner> winnersList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + WINNERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Winner winner = new Winner();
                winner.setId(Integer.parseInt(cursor.getString(0)));
                winner.setUsername(cursor.getString(1));
                winner.setPoints(Integer.parseInt(cursor.getString(2)));
                winnersList.add(winner);
            } while (cursor.moveToNext());
        }
        return winnersList;
    }
}
