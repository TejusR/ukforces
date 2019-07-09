import com.example.ukforces.Forces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    String BASE_URL="https://data.police.uk/api/";

    @GET("forces")
    Call<List<Forces>> getForces();
}
