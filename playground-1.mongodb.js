/* global use, db */
// MongoDB Playground
// To disable this template go to Settings | MongoDB | Use Default Template For Playground.
// Make sure you are connected to enable completions and to be able to run a playground.
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.
// The result of the last command run in a playground is shown on the results panel.
// By default the first 20 documents will be returned with a cursor.
// Use 'console.log()' to print to the debug output.
// For more documentation on playgrounds please refer to
// https://www.mongodb.com/docs/mongodb-vscode/playgrounds/

// Select the database to use.
use('local');

// 1. Liste todos os documentos da coleção.
    db.restaurants.find()
    // 3772

//2. Apresente os campos restaurant_id, nome, localidade e gastronomia para todos os documentos da coleção. 
    db.restaurants.find({},{restaurant_id: 1, nome:1, localidade:1, gastronomia:1})
    // 3772

//3. Apresente os campos restaurant_id, nome, localidade e código postal (zipcode), mas exclua o campo _id de todos os documentos da coleção. 
    db.restaurants.find({},{restaurant_id: 1, nome:1, localidade:1, "address.zipcode":1, _id:0}).count()
    // 3772

//4. Indique o total de restaurantes localizados no Bronx. 
    db.restaurants.find({localidade: "Bronx"}).count()
    // R: 309

//5. Apresente os primeiros 15 restaurantes localizados no Bronx, ordenados por ordem crescente de nome. 
    db.restaurants.find({localidade: "Bronx"}, {nome:1, _id:0}).sort({nome: 1}).limit(15)
    //[{"nome": "African Market (Baboon Cafe)"}, {"nome": "African Terrace"}, {"nome": "Al Cholo Bakery"}, {"nome": "Ali'S Roti Shop"}, {"nome": "Ambassador Diner"}, {"nome": "An Beal Bocht Cafe"}, {"nome": "Angelica'S Bakery"}, {"nome": "Applebee'S Neighborhood Grill & Bar"}, {"nome": "Aqueduct North"}, {"nome": "Archer Sports Bar"}, {"nome": "Artie'S"}, {"nome": "Arturo'S Pizza"}, {"nome": "Artuso Pastry Shop"}, {"nome": "Astral Fitness & Wellness"}, {"nome": "Bagel Cafe"}]

//6. Liste todos os restaurantes que tenham pelo menos um score superior a 85. 
    db.restaurants.find({"grades.score": {$gt: 85}}, {nome:1, _id:0})
    //[{"nome": "Murals On 54/Randolphs'S"},{"nome": "Gandhi"},{"nome": "Bella Napoli"},{"nome": "West 79Th Street Boat Basin Cafe"}]

//7. Encontre os restaurantes que obtiveram uma ou mais pontuações (score) entre [80 e 100]. 
    db.restaurants.find({"grades.score": {$gte: 80, $lte:100}}, {nome:1, _id:0})
    // [{"nome": "Murals On 54/Randolphs'S"}, {"nome": "Gandhi"}, {"nome": "Bella Napoli"}, {"nome": "B.B. Kings"}, {"nome": "West 79Th Street Boat Basin Cafe"}]

//8. Indique os restaurantes com latitude inferior a -95,7. 
    db.restaurants.find({"address.coord.0": {$lt: -95.7}}, {nome:1, _id:0})
    //[{"nome": "Burger King"}, {"nome": "Cascarino'S"}, {"nome": "Sports Center At Chelsea Piers (Sushi Bar)"}]

//9. Indique os restaurantes que não têm gastronomia "American", tiveram uma (ou mais) pontuação superior a 70 e estão numa latitude inferior a -65. 
    
    db.restaurants.find(
        {gastronomia: {$ne: "American"}, "grades.score": {$gt: 70}, "address.coord.0": {$lt:-65}},
        {nome:1, _id:0}
    )
    
    // [{"nome": "Gandhi"}, {"nome": "Bella Napoli"}, {"nome": "El Molino Rojo Restaurant"}, {"nome": "Fortunato Bros Cafe & Bakery"}, {"nome": "Two Boots Grand Central"}]

//10. Liste o restaurant_id, o nome, a localidade e gastronomia dos restaurantes cujo nome começam por "Wil". 
    db.restaurants.find({nome: /^Wil/}, {restaurant_id:1, nome:1, localidade:1, gastronomia:1, _id:0})
    /*[{"localidade": "Brooklyn","gastronomia": "Delicatessen","nome": "Wilken'S Fine Food","restaurant_id": "40356483"},
    {"localidade": "Bronx",  "gastronomia": "American","nome": "Wild Asia","restaurant_id": "40357217"},
    {"localidade": "Bronx", "gastronomia": "Pizza","nome": "Wilbel Pizza","restaurant_id": "40871979"}]*/

//11. Liste o nome, a localidade e a gastronomia dos restaurantes que pertencem ao Bronx e cuja gastronomia é do tipo "American" ou "Chinese". 
    db.restaurants.find({localidade: "Bronx", gastronomia: {$in: ["American", "Chinese"]}}, {nome:1, localidade:1, gastronomia:1, _id:0})
    //91

//12. Liste o restaurant_id, o nome, a localidade e a gastronomia dos restaurantes localizados em "Staten Island", "Queens",  ou "Brooklyn". 
    db.restaurants.find({localidade: {$in: ["Staten Island", "Queens", "Brooklyn"]}}, {restaurant_id:1, nome:1, localidade:1, gastronomia:1, _id:0})
    // 1580
 
//13. Liste o nome, a localidade, o score e gastronomia dos restaurantes que alcançaram sempre pontuações inferiores ou igual a 3. 
    db.restaurants.find({"grades.score": {$lte: 3}}, {nome:1, localidade:1, "grades.score":1, gastronomia:1, _id:0})
    //1089

//14. Liste o nome e as avaliações dos restaurantes que obtiveram uma avaliação com um grade "A", um score 10 na data "2014-08-11T00: 00: 00Z" (ISODATE).  
    db.restaurants.find({"grades.grade": "A", "grades.score": 10, "grades.date": ISODate("2014-08-11T00:00:00Z")}, {nome:1, "grades":1, _id:0})
    //10

//15. Liste o restaurant_id, o nome e os score dos restaurantes nos quais a segunda avaliação foi grade "A" e ocorreu em ISODATE "2014-08-11T00: 00: 00Z". 
    db.restaurants.find({"grades.1.grade": "A", "grades.1.date": ISODate("2014-08-11T00:00:00Z")}, {restaurant_id:1, nome:1, "grades":1, _id:0}).count()
    // 2

//16. Liste o restaurant_id, o nome, o endereço (address) dos restaurantes onde o 2º elemento da matriz de coordenadas (coord) tem um valor superior a 42 e inferior ou igual a 52. 
    db.restaurants.find({"address.coord.1": {$gt: 42, $lte: 52}}, {restaurant_id:1, nome:1, "address":1, _id:0})
    // 7

//17. Liste nome, gastronomia e localidade de todos os restaurantes ordenando por ordem crescente da gastronomia e, em segundo, por ordem decrescente de localidade. 
    db.restaurants.find({}, {nome:1, gastronomia:1, localidade:1, _id:0}).sort({gastronomia: 1, localidade: -1})

//18. Liste nome, localidade, grade e gastronomia de todos os restaurantes localizados em Brooklyn que não incluem gastronomia "American" e obtiveram uma classificação (grade) "A". Deve apresentá-los por ordem decrescente de gastronomia. 
    db.restaurants.find({localidade: "Brooklyn", gastronomia: {$ne: "American"}, "grades.grade": "A"}, {nome:1, localidade:1, "grades":1, gastronomia:1, _id:0}).sort({gastronomia: -1})
    //493 

//19. Indique o número total de avaliações (numGrades) na coleção.  
    db.restaurants.aggregate([{$unwind: "$grades"}, {$group: {_id: null, numGrades: {$sum: 1}}}])
    // 18142

//20. Apresente o nome e número de avaliações (numGrades) dos 3 restaurante com mais avaliações. 
    db.restaurants.aggregate([{$unwind: "$grades"}, {$group: {_id: "$nome", numGrades: {$sum: 1}}}, {$sort: {numGrades: -1}}, {$limit: 3}])
    // [{"_id": "Starbucks Coffee", "numGrades": 424}, {"_id": "Mcdonald'S", "numGrades": 385}, {"_id": "Domino'S Pizza", "numGrades": 185}]

//21. Apresente o número total de avaliações (numGrades) em cada dia da semana. 
    db.restaurants.aggregate([{$unwind: "$grades"}, {$group: {_id: {$dayOfWeek: "$grades.date"}, numGrades: {$sum: 1}}}, {$sort: {"_id": 1}}])
    // [{"_id":1,"numGrades":7},{"_id":2,"numGrades":3186},{"_id":3,"numGrades":3878},{"_id":4,"numGrades":4118},{"_id":5,"numGrades":3984},{"_id":6,"numGrades":2440},{"_id":7,"numGrades":529}]
    
//22. Conte o total de restaurante existentes em cada localidade. 
    db.restaurants.aggregate([{$group: {_id: "$localidade", total: {$sum: 1}}}])
    // [{"_id":"Staten Island","total":158},{"_id":"Brooklyn","total":684},{"_id":"Bronx","total":309},{"_id":"Manhattan","total":1883},{"_id":"Queens","total":738}]

//23. Indique os restaurantes que têm gastronomia "Portuguese", o somatório de score é superior a 50 e estão numa latitude inferior a -60. 
    db.restaurants.aggregate([ 
        {$match: {gastronomia: "Portuguese", "address.coord.0": {$lt: -60}}}, 
        {$project: {nome:1, score: {$sum: "$grades.score"}}}
    ])
    // [{"_id":{"$oid":"67113a91f4f91080db45bb1c"},"nome":"Mateus Restaurant","score":67},{"_id":{"$oid":"67113a91f4f91080db45bde0"},"nome":"Pao","score":80}]

//24. Apresente o número de gastronomias diferentes na rua "Fifth Avenue" 
    db.restaurants.distinct("gastronomia", { "address.rua": "Fifth Avenue" }).length
    // 4

//25. Apresente o nome e o score médio (avgScore) e número de avaliações (numGrades) dos restaurantes com score médio superior a 30 desde 1-Jan-2014. 
    db.restaurants.aggregate([
        {$unwind: "$grades"},
        {$match: {"grades.date": {$gte: ISODate("2014-01-01")}}},
        {$group: {_id: "$nome", avgScore: {$avg: "$grades.score"}, numGrades: {$sum: 1}}},
        {$match: {avgScore: {$gt: 30}}},
        {$project: { nome: 1, avgScore: 1, numGrades: 1 }}
    ])

    /*
    [{"_id": "Mesivta Eitz Chaim","avgScore": 36,"numGrades": 1},
    {"_id": "Coppola'S","avgScore": 32.666666666666664,"numGrades": 3},
    {"_id": "Dojo Restaurant","avgScore": 33.5,"numGrades": 2},
    etc
    */
   
//26. .. 30. Descreva 5 perguntas adicionais à base dados (alíneas 26 a 30), significativamente distintas das anteriores, e apresente igualmente a solução de pesquisa para cada questão. 

//26. Apresente o restaurante com a maior média de score.
    db.restaurants.aggregate([
    {$project: {nome: 1,avgScore: { $avg: "$grades.score" }, _id: 0 }},
    {$sort: { avgScore: -1 } }, 
    {$limit: 1 } 
    ])

//27. Apresente a média de pontuação de todos os restaurantes combinados?
    db.restaurants.aggregate([
    {$unwind: "$grades"},
    {$group: { _id: null, avgScore: { $avg: "$grades.score" } } }
    ])

//28. Apresente o restaurante com o menor número de avaliações
    db.restaurants.aggregate([
    {$project: {nome: 1,numGrades: { $sum: 1 }, _id: 0 }},
    {$sort: { numGrades: 1 } }, 
    {$limit: 1 } 
    ]) 

//29. Apresente quantos restaurantes têm uma pontuação média (avgScore) acima de 30
    db.restaurants.aggregate([
    {$project: {nome: 1,avgScore: { $avg: "$grades.score" }, _id: 0 }},
    {$match: { avgScore: { $gt: 30 } } },
    {$count: "restaurantes" }
    ])

//30. Apresenta o número de restaurantes que têm uma avaliação "A" e uma pontuação de 10 e com cozinha "Bagels/Pretzels"
    db.restaurants.aggregate([
    {$match: { gastronomia: "Bagels/Pretzels" } },
    {$project: { nome: 1, grades: 1, _id: 0 } },
    {$match: { "grades.grade": "A", "grades.score": 10 } },
    {$count: "restaurantes" }
    ])

