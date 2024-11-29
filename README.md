# AquaSmart
## Versión 1.1

---

| Autor      | José Morillo Almazán |
|------------|----------------------|
| Asignatura | PMDM                 |
| Curso      | 2º DAM (A)           |


### 1. Reportes

``` kotlin
data class Reports(
var name: String,
var clientName: String,
var date: LocalDate,
var description: String,
var image: String

) {
override fun toString(): String {
return "Reports(name='$name', client='${clientName}', dale='$date', description='$description', image='$image')"
}
}
```

![RecyclerView Aquasmart](img%2FScreenshot_20241128_204303.png)



