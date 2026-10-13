import { View, Text } from 'react-native';
import { Feather } from '@expo/vector-icons';
import { UpcomingTransaction } from '../beforeSpend.mock';

type UpcomingTransactionsProps = {
  transactions: UpcomingTransaction[];
};

export function UpcomingTransactions({ transactions }: UpcomingTransactionsProps) {
  return (
    <View>
      <View className="mb-3 mt-2 flex-row items-center">
        <View className="mr-2 h-8 w-8 items-center justify-center rounded-xl bg-indigo-50">
          <Feather name="calendar" size={15} color="#6366F1" strokeWidth={1.8} />
        </View>
        <View className="flex-1">
          <Text className="text-base font-bold tracking-tight text-slate-900">
            Compromissos previstos
          </Text>

          <Text className="mt-0.5 text-xs text-slate-500">
            Gastos previstos para os próximos dias.
          </Text>
        </View>
      </View>

      <View
        className="overflow-hidden rounded-[22px] border border-white bg-white/95"
        style={{
          shadowColor: '#35245B',
          shadowOffset: { width: 0, height: 7 },
          shadowOpacity: 0.06,
          shadowRadius: 16,
          elevation: 2,
        }}>
        {transactions.map((transaction, index) => (
          <View
            key={transaction.id}
            className={`flex-row items-center px-4 py-4 ${
              index < transactions.length - 1 ? 'border-b border-slate-100' : ''
            }`}>
            {/* Ícone */}
            <View className="h-10 w-10 items-center justify-center rounded-xl bg-violet-50">
              <Feather
                name={(['home', 'credit-card', 'wifi', 'tv'] as const)[index % 4]}
                size={18}
                color={transaction.iconColor}
                strokeWidth={1.8}
              />
            </View>

            {/* Informações */}
            <View className="ml-3 flex-1">
              <Text className="text-sm font-semibold text-slate-900">{transaction.title}</Text>

              <Text className="mt-1 text-xs text-slate-500">{transaction.date}</Text>
            </View>

            {/* Valor */}
            <Text className="text-sm font-bold text-slate-900">{transaction.amount}</Text>
          </View>
        ))}
      </View>
    </View>
  );
}
