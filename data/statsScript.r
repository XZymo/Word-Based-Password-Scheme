library(DBI)
library(RSQLite)

#set working directory to the path with the DB file
setwd("./School/COMP3008/Project2/Word-Based-Password-Scheme/data")

#connect to the database
con = dbConnect(SQLite(), "passwordSchemeData.db")

#QUERYING FOR TOTAL NUMBER OF USER LOGINS
totalQuery1 <- dbSendQuery(con, "SELECT (P1ATTEMPTS+P2ATTEMPTS+P3ATTEMPTS) AS totalAttempts from RESULTS")
totalQueryData <- dbFetch(totalQuery1, n = -1)
dbClearResult(totalQuery1)

totalMean <- mean(totalQueryData$totalAttempts, na.rm = FALSE)
totalSD <- sd(totalQueryData$totalAttempts, na.rm = FALSE)
totalMedian <- median(totalQueryData$totalAttempts, na.rm = FALSE)

#QUERYING FOR SUCCSSFUL LOGINS
successfulQuery1 <- dbSendQuery(con, "SELECT ((CASE WHEN (P1ATTEMPTS-3)<1 THEN 1 ELSE 0 END) + (CASE WHEN (P2ATTEMPTS-3)<1 THEN 1 ELSE 0 END) + (CASE WHEN (P3ATTEMPTS-3)<1 THEN 1 ELSE 0 END)) as gAttempts from RESULTS")
successfulQueryData <- dbFetch(successfulQuery1, n = -1)
dbClearResult(successfulQuery1)

successfulMean <- mean(successfulQueryData$gAttempts)
successfulSD <- sd(successfulQueryData$gAttempts, na.rm = FALSE)
successfulMedian <- median(successfulQueryData$gAttempts, na.rm = FALSE)

#QUERYING FOR FAILED LOGINS
failedQuery1 <- dbSendQuery(con, "SELECT ((P1ATTEMPTS-1)+(P2ATTEMPTS-1)+(P3ATTEMPTS-1)) AS unsuccessfulAttempts from RESULTS")
failedQueryData <- dbFetch(failedQuery1, n = -1)
dbClearResult(failedQuery1)

failedMean <- mean(failedQueryData$unsuccessfulAttempts)
failedSD <- sd(failedQueryData$unsuccessfulAttempts, na.rm = FALSE)
failedMedian <- median(failedQueryData$unsuccessfulAttempts, na.rm = FALSE)

#QUERYING FOR SUCCSSFUL LOGIN TIME DATA
successfulTimeQuery1 <- dbSendQuery(con, "SELECT (CASE WHEN P1ATTEMPTS < 4 THEN P1TIME ELSE 0 END) as sTimes from RESULTS")
successfulTimeQueryData <- dbFetch(successfulTimeQuery1, n = -1)
dbClearResult(successfulTimeQuery1)

successfulTimeQuery2 <- dbSendQuery(con, "SELECT (CASE WHEN P2ATTEMPTS < 4 THEN P2TIME ELSE 0 END) as sTimes from RESULTS")
successfulTimeQueryData2 <- dbFetch(successfulTimeQuery2, n = -1)
dbClearResult(successfulTimeQuery2)

successfulTimeQuery3 <- dbSendQuery(con, "SELECT (CASE WHEN P3ATTEMPTS < 4 THEN P3TIME ELSE 0 END) as sTimes from RESULTS")
successfulTimeQueryData3 <- dbFetch(successfulTimeQuery3, n = -1)
dbClearResult(successfulTimeQuery3)

successfulTimeVec <- c(successfulTimeQueryData$sTimes, successfulTimeQueryData2$sTimes, successfulTimeQueryData2$sTimes)
successfulTimeMean <- mean(successfulTimeVec)
successfulTimeSD <- sd(successfulTimeVec, na.rm = FALSE)
successfulTimeMedian <- median(successfulTimeVec, na.rm = FALSE)

#QUERYING FOR FAILED LOGIN TIME DATA
failedTimeQuery1 <- dbSendQuery(con, "SELECT (CASE WHEN P1ATTEMPTS = 4 THEN P1TIME ELSE 0 END) as sTimes from RESULTS")
failedTimeQueryData <- dbFetch(failedTimeQuery1, n = -1)
dbClearResult(failedTimeQuery1)

failedTimeQuery2 <- dbSendQuery(con, "SELECT (CASE WHEN P2ATTEMPTS = 4 THEN P2TIME ELSE 0 END) as sTimes from RESULTS")
failedTimeQueryData2 <- dbFetch(failedTimeQuery2, n = -1)
dbClearResult(failedTimeQuery2)

failedTimeQuery3 <- dbSendQuery(con, "SELECT (CASE WHEN P3ATTEMPTS = 4 THEN P3TIME ELSE 0 END) as sTimes from RESULTS")
failedTimeQueryData3 <- dbFetch(failedTimeQuery3, n = -1)
dbClearResult(failedTimeQuery3)

failedTimeVec <- c(failedTimeQueryData$sTimes, failedTimeQueryData2$sTimes, failedTimeQueryData2$sTimes)
failedTimeMean <- mean(failedTimeVec)
failedTimeSD <- sd(failedTimeVec, na.rm = FALSE)
failedTimeMedian <- median(failedTimeVec, na.rm = FALSE)

#GENERATE HISTOGRAMS
hist(totalQueryData$totalAttempts, col = c("red", "yellow", "green"), main="Number of total login attempts", xlab = "Total attempts", ylab = "Number of users")
hist(successfulQueryData$gAttempts, col = c("red", "yellow", "green"), main="Number of successful login attempts", xlab = "Successful attempts", ylab = "Number of users", xaxt = "n")
axis(1, at=seq(0,3, by=1), labels=seq(0,3, by=1))
hist(failedQueryData$unsuccessfulAttempts, col = c("red", "yellow", "green"), main="Number of failed login attempts", xlab = "Failed attempts", ylab = "Number of users", xaxt = "n")
axis(1, at=seq(0,8, by=1), labels=seq(0,8, by=1))
hist(successfulTimeVec, col = c("red", "yellow", "green"), main="Successful login times", xlab = "Time spent logging in(seconds)", ylab = "Number of users")
hist(failedTimeVec, col = c("red", "yellow", "green"), main="Failed login times", xlab = "Time spent logging in(seconds)", ylab = "Number of users")

#GENERATE BOXPLOTS
boxplot(successfulTimeVec, ylab = "Login Time")
boxplot(failedTimeVec, ylab = "Login Time")

#DO T-TESTS AGAINST TEXT28 SCHEME
t.test(totalQueryData$totalAttempts, mu=29.11111)
t.test(successfulQueryData$gAttempts, mu=22.333333)
t.test(failedQueryData$unsuccessfulAttempts, mu=6.777778)
t.test(successfulTimeVec, mu=11.71481481)
t.test(failedTimeVec, mu=9.458333333)




