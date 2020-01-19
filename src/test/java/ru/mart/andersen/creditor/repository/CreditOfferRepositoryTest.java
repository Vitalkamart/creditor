package ru.mart.andersen.creditor.repository;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.mart.andersen.creditor.config.CreditorPostgresqlContainer;
import ru.mart.andersen.creditor.model.CreditOffer;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.TestUtils.getTestCreditOffer;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CreditOfferRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CreditorPostgresqlContainer.getInstance();

    @Autowired
    private CreditOfferRepository creditOfferRepository;

    private static CreditOffer testCreditOffer;

    @BeforeAll
    static void init() {
        testCreditOffer = getTestCreditOffer();
    }

//    @Test
//    @DisplayName("save and get creditOffer")
//    void aTest() {
//        Long id = creditOfferRepository.save(testCreditOffer).getId();
//        testCreditOffer.setId(id);
//        CreditOffer selected = creditOfferRepository.findById(id).get();
//
//        System.out.println(testCreditOffer);
//        System.out.println(selected);
//        assertEquals(testCreditOffer.toString(), selected.toString());
//    }

//    @Test
//    @DisplayName("delete creditOffer")
//    void bTest() {
//        creditOfferRepository.deleteById(testCreditOffer.getId());
//        boolean isFound = creditOfferRepository.findById(testCreditOffer.getId()).isPresent();
//
//        assertFalse(isFound);
//    }
}
