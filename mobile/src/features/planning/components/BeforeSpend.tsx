import { useState } from "react";
import { View, Text } from "react-native";
import { useRouter } from "expo-router";
import { SimulationResult, mockSimulation, upcomingTransactions } from "../beforeSpend.mock";
import { SimulationInput } from "./SimulationInput";
import { SimulationResultCard } from "./SimulationResultCard";
import { UpcomingTransactions } from "./UpcomingTransactions";





export function BeforeSpend() {
  const router = useRouter();

  const [amount, setAmount] = useState("");
  const [result, setResult] = useState<SimulationResult | null>(null);

  const handleSimulate = () => {
    const numericAmount = Number(
      amount.replace(/\./g, "").replace(",", ".")
    );

    if (!numericAmount || numericAmount <= 0) {
      return;
    }

    setResult(mockSimulation(numericAmount));
  };

  const handleViewDetails = () => {
    if (!result) return;

    router.push({
      pathname: "/planning/before-spend-details",
      params: {
        amount,
        status: result.status,
        availableAfter: result.availableAfter,
        marginImpact: result.marginImpact,
        analysis: result.analysis,
      },
    });
  };

  return (
    <View className="w-full">
     
      <View className="mb-4">
        <Text className="text-lg font-bold text-slate-900">
          Antes de gastar
        </Text>

        <Text className="mt-1 text-sm leading-5 text-slate-500">
          Simule uma despesa e entenda o efeito no seu orçamento antes de
          decidir.
        </Text>
      </View>

    
      <SimulationInput
        amount={amount}
        onChangeAmount={setAmount}
        onSimulate={handleSimulate}
      />

     
      {result && (
        <SimulationResultCard
          result={result}
          onViewDetails={handleViewDetails}
        />
      )}

     
      <UpcomingTransactions transactions={upcomingTransactions} />
    </View>
  );
}