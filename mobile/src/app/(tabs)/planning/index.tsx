import { Text, View } from "react-native";

import { BeforeSpend } from "@/features/planning/components/BeforeSpend";
import { ScreenContainer } from "@/app/components/layouts/ScreenContainer";
import AvailableBalanceCard from "@/features/planning/components/AvailableBalanceCard";
import { ScreenHeader } from "@/app/components/ScreenHeader";
import { PlanScreen } from "@/features/planning/components/PlanScreen";
export default function Planning() {
  return (
     <PlanScreen/>
    );
}