package at.fh.swengb.loggingviewsandactivity

import org.junit.*

class ExampleUnitTest {
    @Before
    fun setup() {
        // Runs before a Test is executed
    }

    @After
    fun tearDown() {
        // Runs after a Test was executed
    }

    @BeforeClass
    fun setupClass() {
        // Runs before the FIRSTS test is executed
    }

    @AfterClass
    fun tearDownClass() {
        // Runs after the LAST test was executed
    }

    @Test
    fun test_one() {
        // Implementation of your Testcase
    }

    @Test
    fun test_two() {
        // Implementation of your Testcase
    }
}