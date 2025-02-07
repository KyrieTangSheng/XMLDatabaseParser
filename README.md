# XMLDatabaseParser

## Running Tests

The project includes both unit tests and demonstration tests for XPath queries.

### Running Tests Separately

1. Run only unit tests:
```bash
mvn test -Dtest=XPathEvaluatorTest
```

2. Run only the XPath query demonstrations:
```bash
mvn test -Dtest=XPathQueryDemo
```
This will generate files in the `query_results` directory:
   - `query1_personas.txt`: Finding all personas in the play
   - `query2_caesar_scenes.txt`: Finding scenes where Caesar speaks
   - `query3_caesar_brutus_and.txt`: Finding acts where Caesar and Brutus appear in same scene (using AND syntax)
   - `query4_caesar_brutus_filter.txt`: Finding acts where Caesar and Brutus appear in same scene (using filter syntax)
   - `query5_no_caesar.txt`: Finding acts where Caesar does not appear

3. Run both unit tests and demonstrations:
```bash
mvn test
```

### Running XPath Queries from Command Line

You can run individual XPath queries using the main program:

```bash
mvn compile exec:java -Dexec.args="<xml-file> '<xpath-query>'"
```

Examples:
```bash
# Find all personas
mvn compile exec:java -Dexec.args="src/test/resources/j_caesar.xml '//PERSONA'"

# Find scenes where Caesar speaks
mvn compile exec:java -Dexec.args="src/test/resources/j_caesar.xml '//SCENE[SPEECH/SPEAKER/text()=\"CAESAR\"]'"

# Find acts with Caesar and Brutus in same scene
mvn compile exec:java -Dexec.args="src/test/resources/j_caesar.xml '//ACT[SCENE[SPEECH/SPEAKER/text()=\"CAESAR\" and SPEECH/SPEAKER/text()=\"BRUTUS\"]]'"
```

Note: For queries containing spaces or special characters, make sure to properly quote the arguments.

### Test Descriptions

1. Unit Tests (`XPathEvaluatorTest.java`):
   - Basic XPath functionality tests
   - Node selection tests
   - Filter condition tests
   - Axis navigation tests

2. Query Demonstrations (`XPathQueryDemo.java`):
   The following queries are executed and their results are saved in separate files in the `query_results` directory:
   - `query1_personas.txt`: Finding all personas in the play
   - `query2_caesar_scenes.txt`: Finding scenes where Caesar speaks
   - `query3_caesar_brutus_and.txt`: Finding acts where Caesar and Brutus appear in same scene (using AND syntax)
   - `query4_caesar_brutus_filter.txt`: Finding acts where Caesar and Brutus appear in same scene (using filter syntax)
   - `query5_no_caesar.txt`: Finding acts where Caesar does not appear

The demonstration results are saved to `xpath_query_results.txt` for analysis and verification.