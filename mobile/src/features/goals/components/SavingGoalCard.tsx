import { colors } from "@/constants/colors";
import { Ionicons } from "@expo/vector-icons";
import { Text, View } from "react-native";

type SavingGoalCardProps = {
  currentAmount: number;
  targetAmount: number;
  percentage: number;
};

export function SavingGoalCard({ currentAmount, targetAmount, percentage }: SavingGoalCardProps) {
  const formatCurrency = (value: number) => {
    return value.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
  };

  const remaining = targetAmount - currentAmount;

  return (
    <View className="mb-4 rounded-2xl bg-white p-5 shadow-sm">
      <View className="mb-4 flex-row items-center justify-between">
        <View className="flex-row items-center">
          <View className="h-10 w-10 items-center justify-center rounded-xl bg-indigo-50">
            <Ionicons name="wallet-outline" size={21} color={colors.primary} />
          </View>
          <View className="ml-3">
            <Text className="text-xs font-medium text-slate-500">Meta de economia</Text>
            <Text className="text-base font-bold text-slate-900">Economizar este mês</Text>
          </View>
        </View>
        <Ionicons name="ellipsis-horizontal" size={20} color="#94A3B8" />
      </View>

      <View className="mb-2 flex-row items-end justify-between">
        <Text className="text-2xl font-bold text-slate-900">
          {formatCurrency(currentAmount)}
        </Text>
        <Text className="text-sm font-medium text-slate-500">
          de {formatCurrency(targetAmount)}
        </Text>
      </View>

      
      <View className="h-2 overflow-hidden rounded-full bg-slate-100">
        <View
          className="h-full rounded-full bg-indigo-600"
          style={{ width: `${percentage}%`, backgroundColor: colors.primary }}
        />
      </View>

      <Text className="mt-3 text-xs font-medium text-slate-500">
        Faltam {formatCurrency(remaining)} para atingir sua meta.
      </Text>
    </View>
  );
}