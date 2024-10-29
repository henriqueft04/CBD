
populatePhones = function (country, start, stop) {

    var prefixes = [21, 22, 231, 232, 233, 234 ];
    for (var i = start; i <= stop; i++) {

        var prefix = prefixes[(Math.random() * 6) << 0]
        var countryNumber = (prefix * Math.pow(10, 9 - prefix.toString().length)) + i;
        var num = (country * 1e9) + countryNumber;
        var fullNumber = "+" + country + "-" + countryNumber;

        db.phones.insert({
            _id: num,
            components: {
                country: country,
                prefix: prefix,
                number: i
            },
            display: fullNumber
        });
        print("Inserted number " + fullNumber);
    }
    print("Done!");
}

countPrefixes = function (prefix) {
    var count = db.phones.find({"components.prefix": prefix}).count();
    print("Prefix " + prefix + " has " + count + " numbers");
}

numerosUnicos = function () {
    var unicos = db.phones.find().toArray().filter(function (phone){
        var num = phone.components.number.toString();
        var digits =  new Set(num);
        return digits.size === num.length;
    });

    print("Números únicos:");
    unicos.forEach(function (phone) {
        print("Full number: " + phone.display);
    });

    print("Total: " + unicos.length);

}