import { View, Text } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { UpcomingTransaction } from "../beforeSpend.mock";



type UpcomingTransactionsProps = {
  transactions: UpcomingTransaction[];
};

export function UpcomingTransactions({
  transactions,
}: UpcomingTransactionsProps) {
  return (
    <View>
      <View className="mb-3">
        <Text className="text-base font-bold text-slate-900">
          Compromissos previstos
        </Text>

        <Text className="mt-1 text-xs text-slate-500">
          Gastos que já estão previstos para os próximos dias.
        </Text>
      </View>

      <View className="overflow-hidden rounded-2xl border border-slate-100 bg-white">
        {transactions.map((transaction, index) => (
          <View
            key={transaction.id}
            className={`flex-row items-center px-4 py-4 ${
              index < transactions.length - 1
                ? "border-b border-slate-100"
                : ""
            }`}
          >
            {/* Ícone */}
            <View className="h-10 w-10 items-center justify-center rounded-xl bg-slate-50">
              <Ionicons
                name={transaction.icon as any}
                size={19}
                color={transaction.iconColor}
              />
            </View>

            {/* Informações */}
            <View className="ml-3 flex-1">
              <Text className="text-sm font-semibold text-slate-900">
                {transaction.title}
              </Text>

              <Text className="mt-1 text-xs text-slate-500">
                {transaction.date}
              </Text>
            </View>

            {/* Valor */}
            <Text className="text-sm font-bold text-slate-900">
              {transaction.amount}
            </Text>
          </View>
        ))}
      </View>
    </View>
  );
}