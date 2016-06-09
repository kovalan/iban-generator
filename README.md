#IBAN GENERATOR

This is a tool for generating test IBAM's.  Currently supported countries are Germany, Austria and the Netherlands.

Client can invoke generate() method by passing the country code.  Valid contry codes are DE (Germany), AT(Austria) and NL(the Netherlands).
Client can also invoke the generate() method without passing the country code, during which the IBAN Generator tool will randomly pick one among DE, AT and NL codes and generate the IBAM for that country.

Examples:

IbanGenerator generator = new IbanGenerator();

generator.generate(); // returns IBAM as a string for randomly selected country.

generator.generate(countryCode); // returns IBAM as a string for the countryCode. Currently supported country codes are DE, AT and NL.

