# Akka Http data from 3rd party
<hr>

#### Fetching required data from third party API and save to Database and to Csv file.
### <u>Table of contents:</u>
1) Scala version
2) Sbt version
3) Steps to run
4) Dependencies used
5) Features

1. **Scala version : 2.13.1** <br><br>
2. **Sbt version : 1.3.2**<br><br>

3. `Steps to run project:` <br>
`1) sbt run `

    `Steps to run test files:` <br>
    `1) sbt test `

     `To generate coverage report:` <br>
      `1) sbt coverageReport`
4. #### Dependencies used:
<ul>
<li> Akka Actors 2.6.8 </li>
<li> Akka http 10.2.1 </li>
<li> Akka Streams 2.6.8 </li>
<li> BSON Codec 1.0.1 </li>
<li> Mongo-Scala driver 2.7.0 </li>
<li> Akka-http-spray-json 10.2.1 </li>
<li> Akka-http-xml 10.2.1 </li>
<li> Xstream 1.4.11.1 </li>
<li> Scala-logging 3.9.2 </li>
<li> Classic logging 1.2.3 </li>
<li> scalatest-flatspec 3.2.2 </li>
<li> opencsv 3.4 </li>
</ul>

5. #### Features:
    a) Fetch data from Third party API <br>
    b) Using actors to download data from API <br>
    c) Save data to database <br>
    d) Save data to csv file