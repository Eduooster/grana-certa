import { View, Text } from "react-native";
import { ScreenContainer } from "../components/layouts/ScreenContainer";
import { HomeHeader } from "@/features/home/components/HomeHeader";
import { BalanceCard } from "@/features/home/components/BalanceCard";
import { balanceMock, homeHeaderMock } from "@/features/home/mock/home.mock";
import { FinancialSummary } from "@/features/home/components/FinancialSummary";
import { InsightCard } from "@/features/home/components/InsightCard";
import { RecentTransactions } from "@/features/home/components/RecentTransactions";
import { HomeScreen } from "@/features/home/components/HomeScreen";



export default function Home() {
  return (  
    <HomeScreen/>
  
  );
}