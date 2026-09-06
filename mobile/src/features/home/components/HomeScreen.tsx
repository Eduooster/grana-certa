import { ScreenContainer } from "@/app/components/layouts/ScreenContainer";
import { homeHeaderMock, balanceMock } from "../mock/home.mock";
import { BalanceCard } from "./BalanceCard";
import { FinancialSummary } from "./FinancialSummary";
import { HomeHeader } from "./HomeHeader";
import { InsightCard } from "./InsightCard";
import { RecentTransactions } from "./RecentTransactions";

export function HomeScreen() {
  return (
     <ScreenContainer>
      <HomeHeader data={homeHeaderMock} />
      <BalanceCard data={balanceMock} />
      <FinancialSummary income={0} expenses={0} result={0}/>
      <InsightCard/>
      <RecentTransactions/>
   </ScreenContainer>
  );
}