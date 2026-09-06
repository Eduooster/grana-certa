import { colors } from "@/constants/colors";
import { cardShadow } from "@/constants/layout";
import { Feather, Ionicons } from "@expo/vector-icons";
import { Pressable, Text, View } from "react-native";



export type Transaction = {
  id: string;
  title: string;
  category: string;
  date: string;
  amount: number;
  type: "income" | "expense";
  icon: keyof typeof Feather.glyphMap;
  iconBg: string;
  iconColor: string;
};

// Dados de exemplo baseados na imagem
const TRANSACTIONS: Transaction[] = [
  {
    id: "1",
    title: "Salário",
    category: "Receita",
    date: "Hoje",
    amount: 4200,
    type: "income",
    icon: "briefcase",
    iconBg: "#FEF2F2", // bg-green-50
    iconColor: colors.success,
  },
  {
    id: "2",
    title: "Mercado Extra",
    category: "Alimentação",
    date: "Hoje",
    amount: 186.40,
    type: "expense",
    icon: "shopping-cart",
    iconBg: "#FEF2F2", // bg-red-50
    iconColor: colors.danger,
  },
  {
    id: "3",
    title: "Netflix",
    category: "Assinaturas",
    date: "Ontem",
    amount: 55.90,
    type: "expense",
    icon: "play-circle",
    iconBg: "#EEF2FF", // bg-indigo-50
    iconColor: colors.primary,
  },
];

export function RecentTransactions() {
  const formatCurrency = (value: number) => {
    return value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
    });
  };

  return (
    <View className="mb-8">
      <View className="mb-4 flex-row items-center justify-between px-1">
        <Text className="text-xl font-bold" style={{ color: "#1F2937" }}>
          Últimas transações
        </Text>
        <Pressable hitSlop={12}>
          <Text className="text-sm font-bold" style={{ color: colors.secondary }}>
            Ver todas
          </Text>
        </Pressable>
      </View>

      <View 
        className="overflow-hidden rounded-[24px]"
        style={[{ backgroundColor: "#FFFFFF" }, cardShadow]}
      >
        {TRANSACTIONS.map((transaction, index) => {
          const isLast = index === TRANSACTIONS.length - 1;

          return (
            <View 
              key={transaction.id}
              className={`flex-row items-center p-5 ${!isLast ? "border-b" : ""}`}
              style={{ borderColor: "#F3F4F6" }}
            >
              <View 
                className="mr-4 h-12 w-12 items-center justify-center rounded-2xl"
                style={{ backgroundColor: transaction.iconBg }}
              >
                <Feather name={transaction.icon} size={20} color={transaction.iconColor} />
              </View>

              <View className="flex-1 justify-center">
                <Text className="text-base font-bold" style={{ color: "#1F2937" }}>
                  {transaction.title}
                </Text>
                <Text className="mt-1 text-xs font-medium" style={{ color: "#6B7280" }}>
                  {transaction.category} • {transaction.date}
                </Text>
              </View>

              <Text 
                className="text-base font-extrabold"
                style={{ color: transaction.type === "income" ? colors.success : "#1F2937" }}
              >
                {transaction.type === "income" ? "+" : "-"}
                {formatCurrency(transaction.amount)}
              </Text>
            </View>
          );
        })}
      </View>
    </View>
  );
}