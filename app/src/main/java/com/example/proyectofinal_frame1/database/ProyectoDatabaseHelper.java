package com.example.proyectofinal_frame1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProyectoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOMBRE = "proyectoFinal";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLA_CATEGORIA = "categoria";
    //public static final String TABLA_SUBCATEGORIA = "subcategoria";
    public static final String TABLA_PRENDA = "prenda";
    public static final String TABLA_USUARIO = "usuario";
    public static final String TABLA_PRENDASXCONJUNTOS = "prendasXConjuntos";
    public static final String TABLA_CONJUNTO = "conjunto";
    public static final String SALT = "salt";

    public ProyectoDatabaseHelper(Context context){
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Código para crear las tablas de la base de datos
        //Tabla categorías
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLA_CATEGORIA +"(\n" +
                "id	INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
                "nombre	TEXT NOT NULL UNIQUE)"
        );
        //inserción de categorías por defecto
        db.execSQL("INSERT INTO " + TABLA_CATEGORIA + " (nombre) VALUES ('Parte de arriba'), ('Parte de abajo'), ('Zapatos'), ('Complementos'), ('Accesorios')");

        //tabla usuarios
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_USUARIO+ "(\n" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "email TEXT NOT NULL UNIQUE,\n" +
                "nombre TEXT NOT NULL,\n" +
                "contrasena TEXT NOT NULL UNIQUE)"
        );
        db.execSQL("INSERT INTO "+TABLA_USUARIO+" (email, nombre, contrasena) VALUES " +
                "('lll@lll.com', 'lll', 'lll')");

        //tabla prendas
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_PRENDA+ "(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "nombre TEXT NOT NULL,\n" +
                "imagen TEXT NOT NULL,\n" +
                "categoria INTEGER,\n" +
                "usuario INTEGER NOT NULL,\n" +
                "FOREIGN KEY(categoria) REFERENCES categoria(id) ON DELETE SET NULL,\n" +
                "FOREIGN KEY(usuario) REFERENCES usuario(user_id) ON DELETE CASCADE)"
        );
        db.execSQL("INSERT INTO "+TABLA_PRENDA+" (nombre, imagen, categoria, usuario) VALUES " +
                "('Camisa', 'https://www.trajesguzman.com/media/1624/camisa-basica-blanca.jpg', 1, 1), " +
                "('Camiseta blanca', 'https://www.disfracesantifaz.com/2038-large_default/camiseta-chica-blanca.jpg', 1, 1), " +
                "('Blusa verde', 'https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fcc%2Fdc%2Fccdcffa050547931b6cc535b6f4bef0d270e12ee.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_shirtsblouses_blouses%5D%2Ctype%5BDESCRIPTIVESTILLLIFE%5D%2Cres%5Bm%5D%2Chmver%5B2%5D&call=url[file:/product/main]', 1, 1), " +
                "('Chandal rosa', 'https://hmperu.vtexassets.com/arquivos/ids/3024520-483-725/Pantalon-de-buzo---Rosado---H-M-PE.jpg?v=637983998116170000', 2, 1), " +
                "('vaqueros pitillo', 'https://media.vogue.es/photos/5cc7367f92f813c7264e4eb6/master/w_1280,c_limit/pantalones_vaqueros_pitillo_mango_basicos_armario_2019_7371.jpg', 2, 1), " +
                "('pantalones blancos', 'https://media.vogue.es/photos/5cc750398f6f6723b65bea04/master/w_1280,c_limit/pantalones_blancos_929.jpg', 2, 1), " +
                "('Converse blancas', 'https://media.revistagq.com/photos/6093ca05235a5910299c929c/master/w_1280,c_limit/converse.jpeg', 3, 1), " +
                "('Tacón lunares', 'https://www.angari.es/wp-content/uploads/2018/09/zapatos-lunares-negros-con-lazos.jpg', 3, 1), " +
                "('pulsera corazón', 'https://asset.swarovski.com/images/$size_1450/t_swa103/b_rgb:ffffff,c_scale,dpr_auto,f_auto,w_auto/5524421_png/pulsera-swarovski-infinity--infinity-y-coraz%C3%B3n--blanca--ba%C3%B1o-de-rodio-swarovski-5524421.png', 5, 1), " +
                "('Bolso grande marrón', 'https://monaur.com/wp-content/uploads/2020/12/bolso-shopper-piel-camel.jpg', 4, 1)");


        //tabla conjuntos
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_CONJUNTO+ "(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "nombre TEXT NOT NULL UNIQUE,\n" +
                "usuario INTEGER NOT NULL,\n" +
                "FOREIGN KEY(usuario) REFERENCES usuario(user_id) ON DELETE CASCADE)"
        );
        db.execSQL("INSERT INTO "+TABLA_CONJUNTO+" (nombre, usuario) VALUES " +
                "('primavera', 1), " +
                "('deporte', 1)");

        //tabla prendasXConjuntos
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLA_PRENDASXCONJUNTOS+ "(\n" +
                "id_prenda INTEGER NOT NULL,\n" +
                "id_conjunto INTEGER NOT NULL,\n" +
                "PRIMARY KEY(id_prenda,id_conjunto),\n" +
                "FOREIGN KEY(id_conjunto) REFERENCES conjunto(id) ON DELETE CASCADE,\n" +
                "FOREIGN KEY(id_prenda) REFERENCES prenda(id) ON DELETE CASCADE)"
        );
        db.execSQL("INSERT INTO "+TABLA_PRENDASXCONJUNTOS+" (id_prenda, id_conjunto) VALUES " +
                "(3, 1), " +
                "(6, 1), " +
                "(7, 1), " +
                "(10, 1), " +
                "(2, 2), " +
                "(4, 2), " +
                "(7, 2)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ SALT +"(\n" +
                "id	INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
                "user_id INTEGER NOT NULL UNIQUE,\n"+
                "salt TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Código para actualizar las tablas de la base de datos
    }
}
