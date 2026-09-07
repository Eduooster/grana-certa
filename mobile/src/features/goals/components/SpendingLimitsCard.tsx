import { colors } from "@/constants/colors";
import { Ionicons } from "@expo/vector-icons";
import { Text, View } from "react-native";

type LimitItemProps = {
  icon: keyof typeof Ionicons.glyphMap;
  title: string;
  spent: number;
  limit: number;
  percentage: number;
  progressColor: string;
};

function LimitItem({ icon, title, spent, limit, percentage, progressColor }: LimitItemProps) {
  const formatCurrency = (value: number) => {
    return value.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
  };

  return (
    <View className="mb-4">
      <View className="mb-2 flex-row justify-between">
        <View className="flex-row items-center">
          <Ionicons name={icon} size={18} color="#64748B" />
          <Text className="ml-2 text-sm font-medium text-slate-700">
            {title}
          </Text>
        </View>
        <Text className="text-sm font-semibold text-slate-900">
          {formatCurrency(spent)} / {formatCurrency(limit)}
        </Text>
      </View>

      <View className="h-2 rounded-full bg-slate-100">
        <View
          className="h-full rounded-full"
          style={{ width: `${percentage}%`, backgroundColor: progressColor }}
        />
      </View>
    </View>
  );
}

export function SpendingLimitsCard() {
  return (
    <View className="mb-4 rounded-2xl bg-white p-5 shadow-sm">
      <View className="mb-4">
        <Text className="text-base font-bold text-slate-900">
          Limites de gastos
        </Text>
        <Text className="mt-1 text-xs font-medium text-slate-500">
          Acompanhe quanto ainda pode gastar em cada categoria.
        </Text>
      </View>

      <LimitItem
        icon="restaurant-outline"
        title="Alimentação"
        spent={120}
        limit={400}
        percentage={30}
        progressColor={colors.primary}
      />

      <LimitItem
        icon="game-controller-outline"
        title="Lazer"
        spent={280}
        limit={300}
        percentage={93}
        progressColor="#F59E0B" // amber-400
      />
    </View>
  );
}