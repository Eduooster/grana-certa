import { View, Text, Pressable } from "react-native";
import { Ionicons } from "@expo/vector-icons";


type HomeHeaderType = {
  userName: string;
  isSynced: boolean;
};

type HomeHeaderProps = {
  data: HomeHeaderType;
};

export function HomeHeader({ data }: HomeHeaderProps) {
  const getInitials = (name: string) => {
    const parts = name.trim().split(" ");

    if (parts.length >= 2) {
      return `${parts[0][0]}${parts[1][0]}`.toUpperCase();
    }

    return name.substring(0, 2).toUpperCase();
  };

  return (
    <View className="mb-6 w-full flex-row items-center justify-between">
      {/* Perfil */}
      <View className="h-11 w-11 items-center justify-center rounded-full bg-violet-100">
        <Text className="text-base font-bold text-violet-700">
          {getInitials(data.userName)}
        </Text>
      </View>

      {/* Informações */}
      <View className="ml-3 flex-1">
        <Text className="text-sm text-slate-500">
          Bem-vindo de volta
        </Text>

        <Text
          className="mt-0.5 text-2xl font-bold text-slate-900"
          numberOfLines={1}
        >
          {data.userName}
        </Text>
      </View>

      {/* Notificações */}
      <Pressable
        className="h-11 w-11 items-center justify-center rounded-full border border-slate-100 bg-white"
        hitSlop={8}
      >
        <Ionicons
          name="notifications-outline"
          size={22}
          color="#475569"
        />
      </Pressable>
    </View>
  );
}