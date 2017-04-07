#QUERYING FOR FAILED LOGIN TIME DATA(NOT INCLUDING 0s)
failedTimeQuery1 <- dbSendQuery(con, "SELECT P1TIME as sTimes from RESULTS where P1ATTEMPTS = 4")
failedTimeQueryData <- dbFetch(failedTimeQuery1, n = -1)
dbClearResult(failedTimeQuery1)

failedTimeQuery2 <- dbSendQuery(con, "SELECT P2TIME as sTimes from RESULTS where P2ATTEMPTS = 4")
failedTimeQueryData2 <- dbFetch(failedTimeQuery2, n = -1)
dbClearResult(failedTimeQuery2)

failedTimeQuery3 <- dbSendQuery(con, "SELECT P3TIME as sTimes from RESULTS where P3ATTEMPTS = 4")
failedTimeQueryData3 <- dbFetch(failedTimeQuery3, n = -1)
dbClearResult(failedTimeQuery3)

failedTimeVec <- c(failedTimeQueryData$sTimes, failedTimeQueryData2$sTimes, failedTimeQueryData2$sTimes)
failedTimeMean <- mean(failedTimeVec)
failedTimeSD <- sd(failedTimeVec, na.rm = FALSE)
failedTimeMedian <- median(failedTimeVec, na.rm = FALSE)