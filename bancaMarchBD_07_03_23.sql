PGDMP                         {            postgres    15.1    15.1 .    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    5    postgres    DATABASE     {   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    4264                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            �           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2                        3079    16444    postgis 	   EXTENSION     ;   CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;
    DROP EXTENSION postgis;
                   false            �           0    0    EXTENSION postgis    COMMENT     ^   COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';
                        false    3            �           1255    42123    DeleteTransaction()    FUNCTION     �   CREATE FUNCTION public."DeleteTransaction"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
DELETE FROM transaction 
WHERE CURRENT_TIMESTAMP > end_timedate;
RETURN NULL;
END$$;
 ,   DROP FUNCTION public."DeleteTransaction"();
       public          postgres    false            �            1259    16408    cashier    TABLE     �   CREATE TABLE public.cashier (
    id integer NOT NULL,
    address text,
    available boolean,
    balance numeric,
    cp text,
    locality text,
    photo text,
    "position" public.geography
);
    DROP TABLE public.cashier;
       public         heap    postgres    false    3    3    3    3    3    3    3    3            �            1259    16407    cashier_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cashier_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cashier_id_seq;
       public          postgres    false    219            �           0    0    cashier_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cashier_id_seq OWNED BY public.cashier.id;
          public          postgres    false    218            �            1259    16399    client    TABLE     {   CREATE TABLE public.client (
    id integer NOT NULL,
    account text,
    password text,
    dni text,
    email text
);
    DROP TABLE public.client;
       public         heap    postgres    false            �            1259    16398    client_id_seq    SEQUENCE     �   CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.client_id_seq;
       public          postgres    false    217            �           0    0    client_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;
          public          postgres    false    216            �            1259    25715    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    17495 	   incidence    TABLE     y   CREATE TABLE public.incidence (
    id bigint NOT NULL,
    message text,
    cashier_id bigint,
    client_id bigint
);
    DROP TABLE public.incidence;
       public         heap    postgres    false            �            1259    17498    incidence_id_seq    SEQUENCE     y   CREATE SEQUENCE public.incidence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.incidence_id_seq;
       public          postgres    false    227            �           0    0    incidence_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.incidence_id_seq OWNED BY public.incidence.id;
          public          postgres    false    228            �            1259    16426    transaction    TABLE       CREATE TABLE public.transaction (
    id bigint NOT NULL,
    amount numeric,
    security_code text,
    type boolean,
    cashier_id bigint,
    client_id bigint,
    init_timedate timestamp without time zone,
    end_timedate timestamp without time zone,
    finished boolean
);
    DROP TABLE public.transaction;
       public         heap    postgres    false            �            1259    16425    transaction_id_seq    SEQUENCE     {   CREATE SEQUENCE public.transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.transaction_id_seq;
       public          postgres    false    221            �           0    0    transaction_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.transaction_id_seq OWNED BY public.transaction.id;
          public          postgres    false    220            �           2604    16411 
   cashier id    DEFAULT     h   ALTER TABLE ONLY public.cashier ALTER COLUMN id SET DEFAULT nextval('public.cashier_id_seq'::regclass);
 9   ALTER TABLE public.cashier ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �           2604    16402 	   client id    DEFAULT     f   ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);
 8   ALTER TABLE public.client ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    17499    incidence id    DEFAULT     l   ALTER TABLE ONLY public.incidence ALTER COLUMN id SET DEFAULT nextval('public.incidence_id_seq'::regclass);
 ;   ALTER TABLE public.incidence ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            �           2604    16429    transaction id    DEFAULT     p   ALTER TABLE ONLY public.transaction ALTER COLUMN id SET DEFAULT nextval('public.transaction_id_seq'::regclass);
 =   ALTER TABLE public.transaction ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221            �          0    16408    cashier 
   TABLE DATA           c   COPY public.cashier (id, address, available, balance, cp, locality, photo, "position") FROM stdin;
    public          postgres    false    219   �1       �          0    16399    client 
   TABLE DATA           C   COPY public.client (id, account, password, dni, email) FROM stdin;
    public          postgres    false    217   �2       �          0    17495 	   incidence 
   TABLE DATA           G   COPY public.incidence (id, message, cashier_id, client_id) FROM stdin;
    public          postgres    false    227   <3       �          0    16757    spatial_ref_sys 
   TABLE DATA           X   COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
    public          postgres    false    223   Y3       �          0    16426    transaction 
   TABLE DATA           �   COPY public.transaction (id, amount, security_code, type, cashier_id, client_id, init_timedate, end_timedate, finished) FROM stdin;
    public          postgres    false    221   v3       �           0    0    cashier_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cashier_id_seq', 6, true);
          public          postgres    false    218            �           0    0    client_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.client_id_seq', 2, true);
          public          postgres    false    216            �           0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 106, true);
          public          postgres    false    229            �           0    0    incidence_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.incidence_id_seq', 1, false);
          public          postgres    false    228            �           0    0    transaction_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.transaction_id_seq', 3, true);
          public          postgres    false    220            �           2606    16415    cashier cashier_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cashier
    ADD CONSTRAINT cashier_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cashier DROP CONSTRAINT cashier_pkey;
       public            postgres    false    219            �           2606    16406    client client_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.client DROP CONSTRAINT client_pkey;
       public            postgres    false    217                       2606    17506    incidence incidence_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.incidence
    ADD CONSTRAINT incidence_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.incidence DROP CONSTRAINT incidence_pkey;
       public            postgres    false    227            �           2606    16433    transaction transaction_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.transaction DROP CONSTRAINT transaction_pkey;
       public            postgres    false    221                       2620    42124 $   transaction deleteTransactionTrigger    TRIGGER     �   CREATE TRIGGER "deleteTransactionTrigger" BEFORE INSERT ON public.transaction FOR EACH ROW EXECUTE FUNCTION public."DeleteTransaction"();

ALTER TABLE public.transaction DISABLE TRIGGER "deleteTransactionTrigger";
 ?   DROP TRIGGER "deleteTransactionTrigger" ON public.transaction;
       public          postgres    false    964    221                       2606    16434    transaction cashier    FK CONSTRAINT     w   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT cashier FOREIGN KEY (cashier_id) REFERENCES public.cashier(id);
 =   ALTER TABLE ONLY public.transaction DROP CONSTRAINT cashier;
       public          postgres    false    4091    219    221                       2606    17518    incidence cashier    FK CONSTRAINT     u   ALTER TABLE ONLY public.incidence
    ADD CONSTRAINT cashier FOREIGN KEY (cashier_id) REFERENCES public.cashier(id);
 ;   ALTER TABLE ONLY public.incidence DROP CONSTRAINT cashier;
       public          postgres    false    4091    227    219                       2606    16439    transaction client    FK CONSTRAINT     t   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT client FOREIGN KEY (client_id) REFERENCES public.client(id);
 <   ALTER TABLE ONLY public.transaction DROP CONSTRAINT client;
       public          postgres    false    221    217    4089                       2606    17513    incidence client    FK CONSTRAINT     r   ALTER TABLE ONLY public.incidence
    ADD CONSTRAINT client FOREIGN KEY (client_id) REFERENCES public.client(id);
 :   ALTER TABLE ONLY public.incidence DROP CONSTRAINT client;
       public          postgres    false    217    227    4089            �   =  x���;R�0�Z9�NV��U�z0d2@I�IȌ#Ϙ��69CG�!;��W����'���~w����t,�@�"%�ڡ��B7����^��� ������W2�4�MR�{ �Vjr��r��m;䎜�R�%�Úh��*��h���s�7#bB^�i�-��/���լs�!�5.Ҍ��;5}I�B��&'-v4�ݱ�:.!��M�B$}�Z���c��	$�._2}�����}K_s9g���j�$.�cQ+��w7�G��� ��g�����n�w�y��e�j��.�d�g1ݬ�t䠹����W��?Os�(      �   Z   x�3�L.M�+IT042�� .3�h^bQb^V"BƐS�"�_���6611�445)0���ޜ���jbljdjfhR���� �Z !      �      x������ � �      �      x������ � �      �      x��}Y�����s���ޱ)����$Vm,6/�B!�~��\�y���H�]f̺��;�EA��縇G ����?�o��/���o8�vرƪ�����L���m*l�Si�M�*�Q����SA��\�v+i��r�Lczъ@�����;�UN x�*UU2	L-D�-k��K�x�����_�>��$�/���墛t�"]�'�>g^�r��������[�n]?�P��<E'!A�4)o }hA+�$A%�"n����ߧ:�)�����$����U��'�1XAS=N4�_5	�^dЮ;C���嚩~ʲ/u�T���(��I��j�k�3��})� /26��-���H��m�� �;����P��=)�e�^~m�����]vfp˭6��'�x4\g$�t8�!�_[*�~=`?������{��:K�qP/*��hGT��3�{���^��!�5��Z]��g��d*y���t����ƻ��~�Ul�a u���%w�R"5��7fo�b�0�g���<�w?��$S�=/
t:c���׫�"Sw[mMr�v����2w������}��YJ��xL(7�#X���ߚk�O��#7���	S���U�3�e��A�A�wn	�b�c�9�:���wǬv��ڷޟ$��u:C*�L���7�8�-�TM�h�dcN38M���w1�DA�h��=$u���{�:�z]��W��k>쏗���zv������v`'}����ng6�p��������L���<kO��K{@�컗�}��N���^Xd� �]6�"��),γʹ�ز��՚�ּÔ�S/��ܤ��p���	���{vDXu�t���ڳn��.V[\(���~����o�o���������	�?�Q_�䫜~v�7�>��pGv�Z���2|�� r�N�%����x�E� '����WF�+[� sE8NJM����V�bM���46�?��� Q���"0t��%�=V�Np3]�K���+E	�U���"�Xv��t���\KJk����v1^F�o�����yG.����PȀL7�9Hc^���\Lh��I�{AN��Z��YL#�A�L`j���XT�J�x�������#�,Ǥ�Z���s���:�vxܶ'z�����{1��Ttד⼠�x^O�+%�.�m\���j=5��`�|ԣdr���w��"���J p����̳�0����Q|�.�������$:t5�e�2�qv����Kdt��g��k>�]Q�u�n)�S�5҉����o�'�A[��dџ����}�DaI��)��y�]�ԧK@`�3cH1W�QY�>}B��aY�&M�Ϣﱀ7Yp��j;��!�����7ٿޮs�}3V�dLWEu�/��,�D�����䏉
}�#w����L��v9���cm�
V��~�g�|�\)#`섛U��8���Sz�_sk�*h�1�6���.��<a�@�P��fG%hfo�]�N�n`�ݩwZ�w��������g7)�̡�z��§�e� E�^麕Mt[���0E�c��U������p�1s�;���`�No�7�Х������ ���xx����������e�\�6>F���U���H��L�������W�����u�/���������������y>��N�G����L������CI���1Yi�Dv�9��\�CZ|����k�v!���j��5n*����UyB�%ae�I�6�vt�>~י��s24u��.+�}���1��� l�}�\7���;�Х�[�!B�t3AS.���J7&��_[�����e�r��?/o^�+)�:Z�+.�Ȏhq��ЏYM5i��Z���xq���&�u�� �]�gO
�-���(�.7)�n�Yy�>q�i��Y���)�����wG)O�=�[-��;M��� �� �?a��Q_��]^�N����1�c�ǐ��!�C�;��w���2��Ġ4���r�%�L��������7���������c?Q��}�b�����'��� ��r�	��	�Qj]ϑ�nPX�P��c���P������mQ�#�ib��0�������]���b�T�l�s�;g+|���⟕��b�v*�����z��P��8k�q�c/��K,\��V�N�M���x3~V�qcܯٟV��X�=�#��6R����l���B���+���q\��'��	ӆ�Ï�Skc��ɦc�\�s���W������%��`�u��r[#��]���T��>� �;"���R}?�'��=BAY�v9���ڎ�w��;�s&NG�QD�9����
���3ޣ�"O<��g�[<�[�w�-T�s�0�QE��;������!o��3��E��'��vx�&SOE��Ė������0u�߸���J�	g
j�*?.����-���ߏI��s�/-%|�`�ɐ�&k5�&�z�oD�3����ٚ2~�+��I��]�8��!�]�l��r�n8�_�>:�-���n���#��?q�'J��i��y�c/HJ=����,+��,��dr�A�_C���M����bcb�t�����?��]���������.[3��XȾ�[�P����t�D���m�6�����vm�@�DB���E���v5ec�oTp���0	_ҪR�e�H�pv�4Zb7�\��1�\$�M���Du�����}�?����Bw�	���F\�C�oYN�u�����w�:�Uӗ{�|�g���26�,g,��F�����^��8o��2�^�G�ĳ��e�ڿ[vZ�h �G������n�En]�"۾�Г��^tW��\��7�-D[�hxy
1d�P��6�m{������Yz�U/�m:�)��Vj�il��M�=Y	Q���?�$[�t�R�,٧2v�-�y�ra��:d�}$Y�?\���`��������|9��3z��������qH�n~s<*�Z"R!�1=��ҥ��h�#�=�,���Ɍ(��)6�� �q⺰Q����I�Jϰ�;(����؞��`K�Mݰ�Yow�j�s���:�
BnV����79zCN"~���(�#I(2I��MKh8AB�L`,���"q

�$����#:"I*���b��!���7x����?S#���C?��"G���w�V�/�v�gY���S:Mo��IgM1A�TةڈX��
��z���������%' /W%�=e)����
������޼?��ݯ�ď�������x�`7�8o�s�Z�������}��?ȸ7�=g��lfɝ�@|����*e�B��5e/�6�IZo��[Z�����ѧ_��::�7�u;Z���v��)��`��3����=o�Oe���̳e8�#��?����u�&���K���7��%�����h`�6Ƒi����H������`v*2	q���һ�ޯɜ�|3��e��m�T�$����Mh�.O��5��3sBX�g��UR͛����:�~� ���~�6K.�芺t���P�}7{����p
�z5X�����L���׸p�����������a��E{��lzK[㙹3J�3�zu�}h�@�g�OES6�=�c���<1��{�k#��%Z;�6�U�m�^7y�b��k��!7�AfZ�ƈY�p�~b>��?��Л��&�n?�����
Vl'5;�|�_0�{�%�ո�v����sזO�^vZ�h2:M׳���/�q��,;+>Ώ��=�b��l�)�ޘ�:����k��t'~��/X���=�m��
Ƹw�Z����Eд���������������1��w@|���?�?��Y�u|�Nj��g��Ne�y�Gg�ޯmn$��wfӻ�����=��e�_\1�"���q�
�J/�%YO:��?��m�{t��[�'ʶ<��},Q���� ���6do3W�|���*���ne.�)�c�����u��~-ٓqP�]�X��odˑ�#����
{*i����k�-W-fE�?��(��Xc��N>{Pq17UqE*�»���%�ρ<ى���<O�SΞ��v��U�����Z4���[	�Z:�	v�S�w�؞>Q_��pI�    �$�S��'�\�x�<���.$��ٱ�R���}�o���p��n����f,�b��?�'�� �/b�M<9��S�3�) �]NG�5��r&I-"|Ox�
�Je!0e�aC\�~�v��v�X��i	�A�M�4�l�?E�KMAq)�ȃN���=5s�.�b�&7��mFd�?A���>�^dc�$k��>.�s��Y=����N�2���L�w��e�n�^0<#�kS�`����S.��v��@���M���[�\d}�aqβ&W�]��<7���/��=x�z`8p��yeK�{Y3��m�r���cf�&��9z�Lb��M��b'j��/H��N�S��)U��8��S�?'g�8�-l����7������sB��������w��z5n&�^,vO�Kl�[8>Ll�[��O#,ݪaE?����6-�,y�@�"�����瓰��� OL���<��'wO-�rma�_����)wUl���ҋ�)���n���*��;I��W�|��7�Ğr3?��;�_2�g�J�wŌZI���I�xMP*��U�q����1�c$�� (z���Cc������"��~X�2 ��j?n*�d��� �wZ��v�bc��v�ܮ��/n����X�ٞ��zW���j����,��	�&��^}����l~�X�
x�avmN��~<�aT�Ƕ5�{�6�����K}�Q�?BdP*x�V*����υ}�]�i�m�[����n˫�/����sؿ�ٽ-�V�`�8�`�6PLS��n�I�;����`��n��3����P!OFvG����� ��W�y�֦z��\$�(��̡!WpA���E�����?�.���m1��x�!���$0."o'������/��{P>�j����ᚆ$ծ��&̺���A��"�?�+^�-�����liϸ�� HHD`���%��4�0�!���ӟ���_b��O��ևjFr�+��?1�D_1�M����_�Z.�[�����m�,�5+^HUMP��&L��N]6���U�ZO��J=y�A��"�v�Nz\֖CS��E�rR�����m`_S���;��<!��x�Z�[� �g�Q^Z�͸MYz׻�>����P�{�����Ҁ^ʆ���z���w�/�§�v��
#O���AR�f�!\~'a��w���\�<3Ya�	��W3l�.`D��}�g�����+v�?��͈[Lȹb�{�	����]��Ёy u���l�&�3�_I�2�Ș*\;��v�֗����
!�>�����:��)��S�n)s��\&�%WR�P��^�~ꀫA�6����z��Ҕ����R�W��lnW�ؕ�������\�w��v���,��"�{3&��R�%b���{���RÐ�b�x�'�_�g�l����NN��S���$�~�����
��Ֆ���$s���	?p��盜�a'�1��>G~�L\	q%�N� %1���q��Q���	��� �� ��4�?0t�u��?H��"G^�ԭᗭ9�Q!�`0��1D���Q���FAM^,Љ#��D�a�Pv����(���^��˧Ʃgä��yAf�"ԷmA��(��5e�Z���Ǫ����s���oM�-��@�W�: H9�@�G�T���z`�hf'����{Ď\|�@~ד��^5e;��DjW�!�E�:��z�a�ƈ�js��rp��1��`:��%\mb0.��/�#�4ׁ`*��Ǐ�����s�9dr;�ut
�~m�ra�^�w�&|�W���!�Ҳ�6҆�{]�(�.��j���W�p�#F��tP|���c��Q!9D�^�O8*�/"	�$ق��S7�ȃh9��q���R)�#�ch�N{�g�Iz��ߛ�D���V�QX�$�<�ܼ�����4�9ɥ! �3�������XV�"C0�!�kg ��P]:n�.��t�62���-�p�!�@��au H�m�˻Y���[��6 �r�����]�-z�����E.^���s�"`����ˎ# ��	�{��#Ӂ;"�m���D�u'""�mldO��S �92� ����0�V[��0�~x���z/�h�h�j6�&�c&��ݗ$�W,�8���S��/#,\#��E�R�L ��@��H�D��P��/:�~�&����AX�_�6�� k�)�TY��m�99�BiB̜$
IOV��j>=��5�Jԑ�&�B��,fx��T��T�������γ�!	�Ap�*e�.ƽ8�e�GT#`m��;�ɞ����9
N� ��r�M��8
�G�?�I�-C�e_���h)c��@�K�n�v�<�޿�����RFQ��O�S|����1��x�[Y�dK�=��v��P��kQ��!u�A�Tw��F�fDW����@�p̌�}�dAG�6��r��<��KLB�T��Xa� Au�Ot����Y�itt��DJ��뒜\䴁4\�^_���9����%�󽝈�XiR�sΫ`(��Ǳ��ƾc���uU��PZ���H)� l���]�x^�.��̛t�ԄO��9Ie-���0�0�����j�3��� ���J�B�p���1q��VS�����.Q����u��ibs�+z��H����(�����꒛5@��fM[#��Km��z���l�.�f�}8o�i)���9-��A����0#�X�C�.dw�/��=E��-�4H?9=m�v#��><oJDLK��p� ���";��Wy�rH���ʲ�Cb����&�z��YÆ��oO{�kR=2�#fLN��#�6�v�:��%R(k�U'�T�J�y+���FL�IQ^���]�Ej����H$���G�`q�i�^G3f�tR�1O��qҝ�R�y�O���UH�hT�N.�(����rB$�Uhh�r5��=c���4���;�͜�f����'�De�>Æ@�
�Y�D#�)2�rv���嵭"a�.��뱡��y��w"C��tԓ�5�\DPZ�����!�mlC �W9�&G�����o��3;q��e�%"A�Uv�����haO\0�i���d��"�8E�__�����`��@�P	�E�d^\��ay��8ج�"};iǂ��?Fd3l�If���U�ʃS�:��Zęs�V�
��E������ǡ(�=��ux��_��4^��#cF��w-��#`?�����<��: >��A�ɧ{�s���k��fL��7�puNI������M�VB�E�Cjz\���r>�pi1.i ��NȂ� �JM�~|���(�*t3Xt�c++'�O3,������K�T���5"C"֏=��~�$I���1���nm�U�C-DOt5�U��b��dUh(P]҉����њ�Lņ*V�d���ԃ�9�{| َ�����@U�ǌR�V�LR�ʁ�j:6��q'`ĢCrZ��ЁǶ9V��-���ۼ��x�*W�ʔ��Y����Q#)w����$��,��6��e��P�i�y%�s	���Y�1 &�0c��9�Y��X.: �脀cz��1!��!���"�}ׂ��x��l]��aR#��Q�Y��*�0��r��D���@��Xq�؛|�4�A�����XGx�����-�Mu(XOw�<>KP���oĘZp�� i����аZ=�����":��w4e�r���l��l+0@��]*B���r������k�XL��$)�����{��坸mm���*��_��]��N�O0v�9n���K'���̐��l��YY��89`���zW�nw���0�0W� {\*m1!1�%)j��T���^`u" ���wf�0��U�T�Ax<#t����E�[ђ�'�<�J�!�]s�����Ή!�Ʉ#/����,�_I�7�(w�h[�3����$8z�y��3�Y߱�jqh�UP|�VwV8���hV��ل�\�l�zo
:��ķ}3t��o
�x�	��q��ê	�`o���R�ø/��婔����A�L�EU�f�q��7�;�պ��ֆ�Wv��p'�?FE�u����k    躃��d9�R��Z�T:��|u��c�Wu�-�!^�V�r_<��'��,ِR�M	�Tk��1��q��3H� S���7��ܾ��h<�E�!�xo�<����-�<?��56J���c�/q�?�jS[��QtW�:�JZ�r��k~i�9�=kjG��+m���3i����Mb�{�bZ
P%��ד7#�(;h��DV6噦Ao�#~~�(%�ҳ5��~���D�ˌ�̐���7t��uY��=�X��p�a� ���Ns�k"A�
�f����E�]o?n��`���!ѨQ��d�T���l�:�e���y 랗�l�����+������&&�v��G���u��ϳ��A$%�NƛF�{���H����*���A��#��	��kV/tA^<�d�L�%.%�.�R�d	@q�����cf�� Z�O@� A
WNmAf�jp�Ż�hr)�;��7�,�7��y:͹�'M�6`<hxlP�d���Bj������㬍�K��j��
O�f��SI�|�ȃ�&�ss+��$�*��0􉮬�̾d����Z^�)*.A5Զލ��^�Gg�s���~,��� �qa熋%�cZg�k�u�Q���f�f��{=8.��3v��%<V}�3������ݚ7/�c*ʬ@-��@^�l���*��i��L�� (fxk]���@��#1a'f�nf�` 2vr��>�a,�����I=g�.����EGrh�����Q��X-��&�ݱs������>*N�bnBUlew�ɺ�g|Vvr+P~1���)9/��@E�6c.��^m\RwYG��
����mr_��b�k��B�Z�3����jt��S�I����>nO�@Z�V�x��M�3vv���സ��k/5I5���Fo��&7�r���F�0�G�����X�U9P��7N��3|��O:\z�8��:KK���:�X��0pd������,/��c��Lf+��劗O����9��7�ĮD�<�/�~F��PS-�P�����:Zeyi��ئI�0���Cu�o�v{����V	*-U
�_��|�!�m�F~��E��A����a�&��� �0h�����e(�����=� �AHEe����[1G�ND�?�#i /�"���h椶� ��(W��җP�����/�5�I�M�^�fxB�r�0��3���N�ڧ@7�9�Uh*��4�l�\�������!��DG�1��{����1�僣�ƈ8�{�������^��~ri�#�ڪgzΘ��g� �$r-��u�25�B�Z�J�,@�9�˼�R�P�J����ds��I�B�W��=��F�-�[(�E�����#I��_f�����?�ln�v��c�t\��#F�(۷3t��lC�z1�wꦪ����T���ԪI�-m7K�v���hpjH��|Bh�J��_�z�q'�rGe����Ǒ4�7l��4]FYקEA�f�������8^pU�WQXN��n�?>6���V|J���M�~�Ɠ��ֱU���WHpZ1���,6�
��&��y�L����x�N�Э*'MR�nw@U�8cLzdg���E�:�/�H������OG�׭��3-n����! ��HP��|KRƌ=��P���z��h8�p�FE&��P�����1�N&t�c���Z�p���({s"�����G����X��=�0�b#�t����^���z�����d�j��O=����O�A��9�*Gn��>!�BX���R\o�'�cI��[���n|�4���f�m�{���'g�,
�
���$�6n�@��򒊈�8�zv
�ܶ��uN�.᫖�W��RQ���,���Cn.\c��3�[
��|XK���V7��o`nKC����!G�w�9�dR�������'$bwF��堆j���΋v����*!��M�pX7��h��"% r���)���!G�>a��6� Р�t�r1��xV�J��|#ǭ9እ@����>�>@^�ь�)Jh����I��.�Ȭ~Y'�6Փ�� jt��iJՌt瞑�̑���]�$	fl��{ �Mb�j�<ng�srt�Fw���(���,�l�2���>��=r#||���p�]�"q��0 1l��qιKX�E~���f�`hl23�>a/7��1���LTه[�Ҕ�Z���B��Ѹ$sHE=�6$=*�����:0�5������F������6;��e�#�,[���v͹��פh-��ཿ�iMf�l��4�#�'|�vB��N����HX�����g&[Ѡ���h��H��sB[����5b�!�Ԁ1#d1�s�ȒS�BC�[��b��1u�i=���4��J������>�=�*D8J㾇�s�@��g�}&�����6I@fl8�'�tT��EˇN�غ6���D�~�����ȱW���k��вI^{��
B���Mx����a�q��bE����#����M�lA���Kw�����F��J�����^���rc��5�qˣ�yL{8�n>��.���N�����<��8�ꈕ����f��3>���N0�+s Nt��Hj�S��}���Ղ�\��|��8��?+=���w64u7$p=���������L�ci�r�8�S3<W���
�(*�0+#�YQ"��}�3샭�t�3@��*�1����Jt��� ]`+�O�f����n5��ڕ�E�*���M���3��K"����]�K�^Pບ��U���X��W���%����__e����	���l��k64D���Ǒ���Ү.b=K�yL�L�O���l������`�~Ñ��6+YM�1��Cl�3N�� 7ԚP3k	�C��P��Og�J��/d���XL1zQ�$[���VaVU�0
v��"�{��-YO$D`��{3"����uh��M��R�^������*�������<L�a�*KM��)0��P׋�=k�1��r:�K��w�a��1�M�s�bh�.��ĩf�J�Y���Vm ���B��s��7��NF�����@�p<����&K��(�ʭ��X�U*d���4l9+�2S�B�� +XY����\Έ�`?a�v�P_��]>1�F��m�&��ݿDx�)��
c�>[ ����䲰�a`��gۈk���28`���*�|2Ė��ҭX�'ibhC�N��m��Q��v����d<��o�pw�C���9kI�X9�ʄ�\��)ĿfD.�O;�W���x������`ь���`c�8Xx=؜>�>.��tnw�Ӥ˛;��s�J����d�.>ү>�Sd���$����!v(��f�iL�)b˦��em#��[�.�ϔ�:�UM��7����O�}���h����E��.�
�g�tom�^��Q��Kt�������jjT�V��Sg���N�1�M�$}���� ����pi����,R
��8LL�ʖ�)�hfM8cߊ�x)�!`ܚ�{_k�����P�zMڤ�R&qq3��z&�6��B�������Hz�0�n���Ѭ|L��nF��9np����,#0��ʰ��!~�G�'b/�WϿ\O���)����K4,\�4�>�ڎ}�0�����]�iU�Aa^82�Kau�k�z�kh\7�HX��I�U�8D��y�Wi��ˈ�feI�!� g�g"�;�3��f��8A+������x����^U1�{��4}87�/��.�瀎�Ʒ���?���&�iҜ���s"I�;��BR�㎚�̬���W?�B|e$�]>1rb$�aL�1��	��QL������t8҇|O(�'�(��0F� �q�&Q(��0�����-�(�E���_�V��`�7���I��|�������OB(A��4��qD!�It };}7�e���#�?H��˧��[�x0��Q��SS	��GQ4!`���q2���ab� ��B�0��	�_?��x>��������|���w�n��2ݘ��p� �`M�8&0��8ON�0LdB`T!!�L��a4� l  �e��������c/��/��������ctPA�!M$4) <3��4(E	�#X@�4�0����(�%�>:~��Gn�ME�������+J@p�NA!�4Ma���["����Z�q
AC��0�~�8�!~����79�[=5����V�6`�@"$@*A��'a�(� �$"(, q&�F�I醴&ȯ��4��c?����V�.�=:vk=Ln߇��$�$���']&ͧ�0!�(�����F��G}4���}$A�W�>���*��"����K���u?D�$�|�O4�a0L��{�c(�^=�D8O�G�4�L:JPI����������@�W9r�׿��������d@�     