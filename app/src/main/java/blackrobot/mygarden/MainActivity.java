package blackrobot.mygarden;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import blackrobot.mygarden.data.DBContract;
import blackrobot.mygarden.data.DBHelper;
import blackrobot.mygarden.sync.GetPlantasResponse;
import blackrobot.mygarden.sync.SyncronizeTask;
/*



* */
public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DBHelper dbhelper;
    private String apiBaseUri;
    private String plantasUriSegment;
    private GetPlantasResponse responseObject;
    private GetPlantasResponse plantas;
    private Context context;
    private Utils tools;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dbhelper = new DBHelper(getBaseContext());
        this.db = dbhelper.getWritableDatabase();
        this.apiBaseUri = "http://localhost:81/SLIM/TestRestService/Test.php";
        this.plantasUriSegment = "/getPlantas";
        this.context = getBaseContext();
        this.tools = new Utils(context);

        final TextView tvResult = (TextView)findViewById(R.id.txtResult);
        ImageView btnSync = (ImageView) findViewById(R.id.btn_sync);
        ImageView btnPlantas = (ImageView) findViewById(R.id.btn_plantas);

//        try {
//            //Create a new RestTemplate instance
//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//            //Make the network request
//            String getPlantasUri = apiBaseUri + plantasUriSegment;
//
//            ResponseEntity<GetPlantasResponse> plantas = restTemplate.getForEntity(getPlantasUri, GetPlantasResponse.class);
//
////            ResponseEntity<GetPlantasResponse> response = restTemplate.exchange(
////                    getPlantasUri,
////                    HttpMethod.GET,
////                    null,
////                    GetPlantasResponse.class
////            );
//            //responseObject = response.getBody();
//
//        }catch (Exception ex){
//            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
//        }

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SyncronizeTask task = new SyncronizeTask(context,tvResult, db);
                try {
                    GetPlantasResponse plantasResponse = task.execute().get();
                }catch (Exception e){

                }


                tools.showLongToast("Syncronize Task Success!");
//                String aux = "0";
//                if (responseObject != null){
//                    aux = "" + responseObject.getPlantas().size();
//                }
//                if (plantas != null){
//                    aux = "" + plantas.getPlantas().size();
//                }
//                Toast.makeText
//                        (getBaseContext(), "Sincronizando datos: " + aux, Toast.LENGTH_SHORT )
//                        .show();
            }
        });

        btnPlantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] projection = {
                        DBContract.PlantaEntry.COLUMN_NAME_PLANTA_ID,
                        DBContract.PlantaEntry.COLUMN_NAME_NOMBRE,
                        DBContract.PlantaEntry.COLUMN_NAME_DESCRPCION,
                        DBContract.PlantaEntry.COLUMN_NAME_IMAGEN};

                Cursor c = db.query(DBContract.PlantaEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null);

                Toast.makeText(getBaseContext(), "Elementos : " + c.getCount(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
