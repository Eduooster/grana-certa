import { colors } from "@/constants/colors";
import { cardShadow } from "@/constants/layout";
import { Feather } from "@expo/vector-icons";
import { View, Text } from "react-native";


type FinancialSummaryProps = {
  income: number;
  expenses: number;
  result: number;
};




export function FinancialSummary({ income, expenses, result }: FinancialSummaryProps) {
  const formatCurrency = (value: number) => {
    return value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
      minimumFractionDigits: 0,
      maximumFractionDigits: 0,
    });
  };

  return (
    <View className="mb-8 flex-row justify-between gap-3">
      <View 
        className="flex-1 rounded-[20px] p-4"
        style={[{ backgroundColor: "#FFFFFF" }, cardShadow]}
      >
        <View className="mb-3 flex-row items-center gap-2">
          <View className="rounded-full bg-green-50 p-1.5">
            <Feather name="arrow-down-left" size={16} color={colors.success} />
          </View>
          <Text className="text-xs font-semibold text-gray-500">
            Receitass
          </Text>
        </View>
        <Text className="text-base font-bold" style={{ color: colors.success }}>
          {formatCurrency(income)}
        </Text>
      </View>

      <View 
        className="flex-1 rounded-[20px] p-4"
        style={[{ backgroundColor: "#FFFFFF" }, cardShadow]}
      >
        <View className="mb-3 flex-row items-center gap-2">
          <View className="rounded-full bg-red-50 p-1.5">
            <Feather name="arrow-up-right" size={16} color={colors.danger} />
          </View>
          <Text className="text-xs font-semibold text-gray-500">
            Despesas
          </Text>
        </View>
        <Text className="text-base font-bold" style={{ color: colors.danger }}>
          {formatCurrency(expenses)}
        </Text>
      </View>

      <View 
        className="flex-1 rounded-[20px] p-4"
        style={[{ backgroundColor: "#FFFFFF" }, cardShadow]}
      >
        <View className="mb-3 flex-row items-center gap-2">
          <View className="rounded-full bg-indigo-50 p-1.5">
            <Feather name="credit-card" size={16} color={colors.primary} />
          </View>
          <Text className="text-xs font-semibold text-gray-500">
            Resultado
          </Text>
        </View>
        <Text className="text-base font-bold" style={{ color: colors.primary }}>
          +{formatCurrency(result)}
        </Text>
      </View>
    </View>
  );
}