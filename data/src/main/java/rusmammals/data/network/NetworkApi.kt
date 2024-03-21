package rusmammals.data.network;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rusmammals.data.model.dtos.AuthRequestDto;
import rusmammals.data.model.dtos.EmailDto;
import rusmammals.data.model.dtos.EntryDto;
import rusmammals.data.model.dtos.FeedbackDto;
import rusmammals.data.model.dtos.RegistrationDto;
import rusmammals.data.model.dtos.SpecieDto;
import rusmammals.data.model.dtos.TokenDto;

interface NetworkApi {
    @GET("/api/v1/species?expand=animalOrder,animalFamily,animalGenera,&per-page=1000")
    suspend fun getSpecies(@Header("Authorization") token: String): List<SpecieDto>

    @POST("/api/v1/authentication")
    suspend fun auth(@Body authRequestDto: AuthRequestDto): TokenDto

    @POST("/api/v1/data")
    suspend fun postEntry(@Header("Authorization") str: String, @Body entryDTO: EntryDto)

    @POST("/api/v1/feedback")
    suspend fun postFeedback(
        @Header("Authorization") str: String,
        @Body feedbackDTO: FeedbackDto
    )

    @POST("/api/v1/account/request-password-reset")
    suspend fun postForgetPassword(@Body emailDTO: EmailDto)

    @POST("/api/v1/photo")
    @Multipart
    suspend fun postPhoto(
        @Header("Authorization") str: String,
        @Part part: MultipartBody.Part?,
        @Part part2: MultipartBody.Part?
    )

    @POST("/api/v1/registration")
    suspend fun postRegistration(@Body registrationDTO: RegistrationDto): TokenDto
}
