import { View, Text, Pressable, ScrollView } from "react-native";
import { Ionicons } from "@expo/vector-icons";

import { colors } from "@/constants/colors";
import { ScreenContainer } from "@/app/components/layouts/ScreenContainer";
import { ScreenHeader } from "@/app/components/ScreenHeader";
import { CreateGoalButton } from "./CreateGoalButton";
import { SavingGoalCard } from "./SavingGoalCard";
import { SpendingLimitsCard } from "./SpendingLimitsCard";

export default function GoalsScreen() {
  return (
    <ScreenContainer>
      
      <ScreenHeader 
        title="Metas" 
      
      />

      <SavingGoalCard 
        currentAmount={650} 
        targetAmount={1000} 
        percentage={65} 
      />

      <SpendingLimitsCard />

      <CreateGoalButton onPress={() => console.log("Abrir modal de nova meta")} />

    </ScreenContainer>
  );
}