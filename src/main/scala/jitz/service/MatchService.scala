package jitz.service

import com.google.inject.Inject
import jitz.controller.presentation.Score
import jitz.model.entities.{Competitor, CompetitorModel, Match, MatchId, MatchModel, MatchScore}
import slick.dbio.Effect
import slick.jdbc.PostgresProfile.api._
import slick.sql.FixedSqlStreamingAction

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MatchService @Inject() (db: Database) {

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
    }


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
