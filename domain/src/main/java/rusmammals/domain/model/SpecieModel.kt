package rusmammals.domain.model

import java.io.Serializable

data class SpecieModel(
    val id: Long,
    val latName: String,
    val rusname: String,
    val alien: Boolean?,
    val comments: String?,
    val wEmmaFrame: Boolean?,
    val oEmmaFrame: Boolean?,
    val trueSpecies: Boolean?,
    val description: String?,
    val sort: Int?,
    val author: String?,
    val descYear: Int?,
    val nomen: String?,
    val epid: String?,
    val agricultural: String?,
    val rfStatus: String?,
    val animalFamily: String?,
    val animalGenera: String?,
    val animalOrder: String?
): Serializable