package blackrobot.mygarden.data;

import android.provider.BaseColumns;

/**
 * Created by niko_ on 23/07/2016.
 */
public final class DBContract {

    public DBContract() {
    }

    public static abstract class PlantaEntry implements BaseColumns {
        public static final String TABLE_NAME = "Planta";
        public static final String COLUMN_NAME_PLANTA_ID = "id";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_DESCRPCION = "descripcion";
        public static final String COLUMN_NAME_IMAGEN = "imagen";
    }
}
