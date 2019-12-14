package jitz.service

import com.google.inject.{Inject, Singleton}
import jitz.controller.response.Score
import jitz.model.entities._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MatchService @Inject() (db: Database) {

  def recordPoints(matchId: MatchId, competitorId: CompetitorId, points: Int): Future[Int] = {
     val insert = MatchScore.matchScoreTable.forUpdate += MatchScoreModel(matchId, competitorId, points)
    db.run(insert)
  }

  def scoreForMatch(matchId: MatchId)(implicit ec: ExecutionContext): Future[Score] = {
    val queryScoreA = Match.matchTable
      .filter(_.id === matchId)
      .join(Competitor.competitorTable).on({ case (m, c) => m.competitorA === c.id })
      .join(MatchScore.matchScoreTable).on({ case ((m, c), matchScore) => matchScore.matchId === m.id})
      .map { case ((m,c), score) => (s"${c.firstName} ${c.lastName}", score) }

//    db.run(query).map { seq =>
//      seq.map { case (m, c1, c2 ) =>
//        Score(
//          winningName = s"${c1.firstName} ${c1.lastName}",
//          winningScore = 1,
//          losingName = s"${c2.firstName} ${c2.lastName}",
//          losingScore = 0
//        )
//      }
      Future(Score("hi", 1, "bye", 2))


//      .map { case ((m, c1), c2) =>
//        Score(
//          winningName = s"${c1.firstName} ${c1.lastName}",
//          winningScore = 1,
//          losingName = s"${c2.firstName} ${c2.lastName}",
//          losingScore = 0
//        )
//      }
  }

}

