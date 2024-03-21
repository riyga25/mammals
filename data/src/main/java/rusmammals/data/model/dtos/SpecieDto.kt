package rusmammals.data.model.dtos

import kotlinx.serialization.Serializable
import rusmammals.domain.model.SpecieModel

@Serializable
data class SpecieDto(
    val id: Long,
    val lat_name: String,
    val rus_name: String,
    val alien: Boolean? = null,
    val comments: String? = null,
    val w_emma_frame: Boolean? = null,
    val o_emma_frame: Boolean? = null,
    val true_species: Boolean? = null,
    val description: String? = null,
    val sort: Int? = null,
    val author: String? = null,
    val desc_year: Int? = null,
    val nomen: String? = null,
    val epid: String? = null,
    val agricultural: String? = null,
    val rf_status: String? = null,
    val animalFamily: TaxonDto? = null,
    val animalGenera: TaxonDto? = null,
    val animalOrder: TaxonDto? = null
) {
    fun toDomain(): SpecieModel = SpecieModel(
        id = id,
        latName = lat_name,
        rusname = rus_name,
        alien = alien,
        comments = comments,
        wEmmaFrame = w_emma_frame,
        oEmmaFrame = o_emma_frame,
        trueSpecies = true_species,
        description = description,
        sort = sort,
        author = author,
        descYear = desc_year,
        nomen = nomen,
        epid = epid,
        agricultural = agricultural,
        rfStatus = rf_status,
        animalFamily = animalFamily?.title,
        animalGenera = animalGenera?.title,
        animalOrder = animalOrder?.title
    )
}
