package com.mycompany;

import com.mycompany.model.Country;
import com.mycompany.repository.CountryRepository;
import com.mycompany.repository.JobTypesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CountryRepositoryTest {

    @Autowired
    CountryRepository countryrepo;

    @Test
    public void testAddCountry(){
        Country country = new Country();
        country.setName("Spain");

        Country saveCountry = countryrepo.save(country);
        Assertions.assertThat(saveCountry.getId()).isGreaterThan(0);

    }

    @Test
    public void testListCountries(){
        Iterable <Country> countries = countryrepo.findAll();
        Assertions.assertThat(countries).hasSizeGreaterThan(0);

        for (Country country: countries){
            System.out.println(country);
        }
    }

    @Test
    public void testUpdateCountry(){
        Integer countryid = 18;
        Optional <Country> optionalCountry = countryrepo.findById(countryid);

        Country country = optionalCountry.get();
        country.setName("Germany");
        countryrepo.save(country);

        Country updateCountry = countryrepo.findById(countryid).get();
        Assertions.assertThat(updateCountry.getName()).isEqualTo("Germany");
    }

    @Test
    public void testDeleteCountry(){
        Integer countryid = 23;
        countryrepo.deleteById(countryid);

        Optional <Country> optionalCountry = countryrepo.findById(countryid);
        Assertions.assertThat(optionalCountry).isNotPresent();
    }
}
