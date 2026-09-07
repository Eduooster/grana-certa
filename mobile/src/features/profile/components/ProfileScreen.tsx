import { ScreenContainer } from "@/app/components/layouts/ScreenContainer";
import { ScreenHeader } from "@/app/components/ScreenHeader";
import { Ionicons } from "@expo/vector-icons";
import { useRouter } from "expo-router";
import { View, Pressable, StyleSheet, Text } from "react-native";

import { colors } from "@/constants/colors";
import { Feather } from "@expo/vector-icons";



const cardShadow = {
  shadowColor: "#000",
  shadowOffset: { width: 0, height: 4 },
  shadowOpacity: 0.05,
  shadowRadius: 12,
  elevation: 2,
};

export default function ProfileScreen() {
  const router = useRouter();

  return (
    <ScreenContainer>
      <View className="mb-6 flex-row items-center justify-between">
        <Text className="text-2xl font-extrabold" style={{ color: "#1F2937" }}>
          Perfil
        </Text>
        
      </View>

      <View 
        className="mb-6 flex-row items-center rounded-[24px] p-5 bg-white"
        style={cardShadow}
      >
        <View 
          className="mr-4 h-16 w-16 items-center justify-center rounded-full"
          style={{ backgroundColor: colors.primary }}
        >
          <Text className="text-xl font-bold text-white">ED</Text>
        </View>
        <View className="flex-1">
          <Text className="text-lg font-bold" style={{ color: "#1F2937" }}>
            Eduardo
          </Text>
          <Text className="text-xs font-medium text-gray-500">
            eduardo@granacerta.com
          </Text>
        </View>
        <Pressable hitSlop={12}>
          <Feather name="edit-2" size={18} color={colors.secondary} />
        </Pressable>
      </View>

      <View className="mb-6">
        <Text className="mb-3 text-xs font-bold uppercase tracking-wider text-gray-400">
          Gerenciamento
        </Text>
        <View className="overflow-hidden rounded-[24px] bg-white" style={cardShadow}>
          <Pressable className="flex-row items-center justify-between p-4 border-b border-gray-100">
            <View className="flex-row items-center gap-3">
              <View className="h-10 w-10 items-center justify-center rounded-xl bg-purple-50">
                <Feather name="link" size={18} color={colors.primary} />
              </View>
              <Text className="text-sm font-bold text-gray-800">Contas e Open Finance</Text>
            </View>
            <Feather name="chevron-right" size={18} color="#9CA3AF" />
          </Pressable>

          <Pressable className="flex-row items-center justify-between p-4">
            <View className="flex-row items-center gap-3">
              <View className="h-10 w-10 items-center justify-center rounded-xl bg-purple-50">
                <Feather name="shield" size={18} color={colors.primary} />
              </View>
              <Text className="text-sm font-bold text-gray-800">Segurança e Senha</Text>
            </View>
            <Feather name="chevron-right" size={18} color="#9CA3AF" />
          </Pressable>
        </View>
      </View>

      <View className="mb-8">
        <Text className="mb-3 text-xs font-bold uppercase tracking-wider text-gray-400">
          Preferências
        </Text>
        <View className="overflow-hidden rounded-[24px] bg-white" style={cardShadow}>
          <Pressable className="flex-row items-center justify-between p-4 border-b border-gray-100">
            <View className="flex-row items-center gap-3">
              <View className="h-10 w-10 items-center justify-center rounded-xl bg-gray-50">
                <Feather name="bell" size={18} color="#4B5563" />
              </View>
              <Text className="text-sm font-bold text-gray-800">Notificações</Text>
            </View>
            <Feather name="chevron-right" size={18} color="#9CA3AF" />
          </Pressable>

          <Pressable className="flex-row items-center justify-between p-4">
            <View className="flex-row items-center gap-3">
              <View className="h-10 w-10 items-center justify-center rounded-xl bg-gray-50">
                <Feather name="help-circle" size={18} color="#4B5563" />
              </View>
              <Text className="text-sm font-bold text-gray-800">Ajuda e Suporte</Text>
            </View>
            <Feather name="chevron-right" size={18} color="#9CA3AF" />
          </Pressable>
        </View>
      </View>

      <View className="mb-10">
        <Pressable 
          className="flex-row items-center justify-center rounded-[20px] p-4 bg-red-50"
        >
          <Feather name="log-out" size={18} color={colors.danger} />
          <Text className="ml-2 text-sm font-bold" style={{ color: colors.danger }}>
            Encerrar sessão
          </Text>
        </Pressable>
      </View>
    </ScreenContainer>
  );
}